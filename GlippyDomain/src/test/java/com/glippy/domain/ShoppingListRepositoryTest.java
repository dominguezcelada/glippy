package com.glippy.domain;

import com.glippy.entity.ShoppingList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/ShoppingListRepositoryTest-context.xml")
public class ShoppingListRepositoryTest {
    @Autowired
    ShoppingListRepository  shoppingListRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @After
    public void tearDown() {
        mongoTemplate.remove(new Query(), ShoppingList.class);
    }

    @Test
    public void testSave() {
        ShoppingList shoppingList = new ShoppingList("Mi Lista").addItem("Coca-Cola",0.53,"mercadona",2).addItem("Beer");

        shoppingList = shoppingListRepository.save(shoppingList);

        assertThat(shoppingList.getId(), notNullValue());
    }

    @Test
    public void testMultipleSave() {
        // Setup
        ShoppingList list1 = new ShoppingList("Test List").addItem("Coca-Cola",2).addItem("Beer"),
                     list2 = new ShoppingList("Another List").addItem("Coca-Cola");

        // Exercise
        shoppingListRepository.save(Arrays.asList(list1, list2));

        // Assertion
        Iterable<ShoppingList> lists = shoppingListRepository.findAll();
        Iterator<ShoppingList> it = lists.iterator();
        int lenght = 0;
        while(it.hasNext()) {
            ShoppingList l = it.next();
            assertThat(l, anyOf(equalTo(list1), equalTo(list2)));
            lenght++;
        }

        assertThat(lenght, is(2));


    }

    @Test
    public void testFindByName() {
        // Setup
        ShoppingList list1 = new ShoppingList("Caprabo").addItem("Coca-Cola",2).addItem("Beer"),
                     list2 = new ShoppingList("Caprabo a casa").addItem("Coca-Cola").addItem("Beer", 2),
                     list3 = new ShoppingList("Another One").addItem("Beer", 2);

        // Exercise
        shoppingListRepository.save(Arrays.asList(list1, list2, list3));

        // Assertion
        List<ShoppingList> lists = shoppingListRepository.findByName("Caprabo");

        assertThat(lists.size(), is(1));
        assertThat(lists.get(0), is(equalTo(list1)));
    }

    @Test
    public void testFindBySimilarText() {
        // Setup
        ShoppingList list1 = new ShoppingList("Caprabo").addItem("Coca-Cola",2).addItem("Beer"),
                     list2 = new ShoppingList("Caprabo Calafell").addItem("Coca-Cola",6).addItem("Beer"),
                     list3 = new ShoppingList("Mercadona a casa").addItem("Coca-Cola").addItem("Beer", 2);

        // Exercise
        shoppingListRepository.save(Arrays.asList(list1, list2, list3));

        // Assertion
        List<ShoppingList> lists = shoppingListRepository.findAllBy(TextCriteria.forDefaultLanguage().matching("Caprabo"));

        assertThat(lists.size(), is(2));
        assertThat(lists.contains(list1), is(true));
        assertThat(lists.contains(list2), is(true));
        assertThat(lists.contains(list3), is(false));
    }
}