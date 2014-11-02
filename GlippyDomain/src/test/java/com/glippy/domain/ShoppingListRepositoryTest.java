package com.glippy.domain;

import com.glippy.entity.ShoppingList;

import org.junit.After;
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

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
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
        ShoppingList shoppingList = new ShoppingList("Mi Lista").addItem("Coca-Cola",2).addItem("Beer");
        shoppingList = shoppingListRepository.save(shoppingList);

        assertThat(shoppingList.getId(), notNullValue());
    }

    @Test
    public void testMultipleSave() {
        // Setup
        ShoppingList list1 = new ShoppingList("Mi Lista").addItem("Coca-Cola",2).addItem("Beer"),
                     list2 = new ShoppingList("Mi Lista").addItem("Coca-Cola").addItem("Beer", 2);

        // Exercise
        shoppingListRepository.save(Arrays.asList(list1, list2));

        // Assertion
        Iterable<ShoppingList> lists = shoppingListRepository.findAll();
        Iterator<ShoppingList> it = lists.iterator();
        int lenght = 0;
        while(it.hasNext()) {
            it.next();
            lenght++;
        }

        assertThat(lenght, is(2));

    }

    @Test
    public void testFindByName() {
        // Setup
        ShoppingList list1 = new ShoppingList("Caprabo").addItem("Coca-Cola",2).addItem("Beer"),
                     list2 = new ShoppingList("Caprabo a casa").addItem("Coca-Cola").addItem("Beer", 2);

        // Exercise
        shoppingListRepository.save(Arrays.asList(list1, list2));

        // Assertion
        List<ShoppingList> lists = shoppingListRepository.findByName("Caprabo");

        assertThat(lists.size(), is(1));
    }

    @Test
    public void testFindAllBy() {
        // Setup
        ShoppingList list1 = new ShoppingList("Caprabo").addItem("Coca-Cola",2).addItem("Beer"),
                     list2 = new ShoppingList("Caprabo a casa").addItem("Coca-Cola").addItem("Beer", 2);

        // Exercise
        shoppingListRepository.save(Arrays.asList(list1, list2));

        // Assertion
        List<ShoppingList> lists = shoppingListRepository.findAllBy(TextCriteria.forDefaultLanguage().matching("Caprabo"));

        assertThat(lists.size(), is(2));
    }
}