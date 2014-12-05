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

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/repositoryDomainTest-context.xml")
public class ShoppingListRepositoryTest {
    @Autowired
    ShoppingListRepository  shoppingListRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @After
    public void tearDown() {
        mongoTemplate.remove(new Query(), ShoppingList.class);
    }



    // Read
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
    public void testFindByUsername() {
        // Setup
        ShoppingList list1 = new ShoppingList("Caprabo").addItem("Coca-Cola",2).addItem("Beer"),
                list2 = new ShoppingList("Caprabo a casa").addItem("Coca-Cola").addItem("Beer", 2),
                list3 = new ShoppingList("Another One").addItem("Beer", 2);

        list1.setUsername("oscardom");
        list2.setUsername("oscar");
        list3.setUsername("oscardom");

        // Exercise
        shoppingListRepository.save(Arrays.asList(list1, list2, list3));

        // Assertion
        List<ShoppingList> lists = shoppingListRepository.findByUsername("oscardom");

        assertThat(lists.size(), is(2));
        assertThat(lists.contains(list1),is(true));
        assertThat(lists.contains(list2),is(false));
        assertThat(lists.contains(list3),is(true));
    }

    @Test
    public void testFindByUsernameAndName() throws Exception {
        // Setup
        ShoppingList list1 = new ShoppingList("Caprabo").addItem("Coca-Cola",2).addItem("Beer"),
                list2 = new ShoppingList("Caprabo a casa").addItem("Coca-Cola").addItem("Beer", 2),
                list3 = new ShoppingList("Another One").addItem("Beer", 2);

        list1.setUsername("oscardom");
        list2.setUsername("oscar");
        list3.setUsername("oscardom");

        // Exercise
        shoppingListRepository.save(Arrays.asList(list1, list2, list3));

        // Assertion
        List<ShoppingList> lists = shoppingListRepository.findByUsernameAndName("oscardom", "Caprabo");

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


    // Save

    @Test
    public void testSave() {
        // Setup
        ShoppingList shoppingList = new ShoppingList("Mi Lista").addItem("Coca-Cola",0.53,"mercadona",2).addItem("Beer");

        // Exercise
        shoppingList = shoppingListRepository.save(shoppingList);

        // Assertion
        assertThat(shoppingList.getId(), notNullValue());
    }

    @Test
    public void testMultipleSave() {
        // Setup
        ShoppingList list1 = new ShoppingList("Test List"),
                list2 = new ShoppingList("Another List");

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

    // Delete


    @Test
    public void testDeleteByUsername() throws Exception {
        // Setup
        ShoppingList list1 = new ShoppingList("Caprabo").addItem("Coca-Cola",2).addItem("Beer"),
                list2 = new ShoppingList("Caprabo Calafell").addItem("Coca-Cola",6).addItem("Beer"),
                list3 = new ShoppingList("Mercadona a casa").addItem("Coca-Cola").addItem("Beer", 2);

        list1.setUsername("oscardom");
        list2.setUsername("userOscar");
        list3.setUsername("oscar");

        shoppingListRepository.save(Arrays.asList(list1, list2, list3));

        // Exercise
        shoppingListRepository.deleteByUsername("oscardom");
        List<ShoppingList> lists = shoppingListRepository.findByUsername("oscardom");

        // Assertion
        assertThat(lists.size(), is(0));

    }

    @Test
    public void testDeleteByUsernameAndName() throws Exception {
        // Setup
        ShoppingList list1 = new ShoppingList("Caprabo").addItem("Coca-Cola",2).addItem("Beer"),
                list2 = new ShoppingList("Caprabo Calafell").addItem("Coca-Cola",6).addItem("Beer"),
                list3 = new ShoppingList("Mercadona a casa").addItem("Coca-Cola").addItem("Beer", 2);

        list1.setUsername("oscardom");
        list2.setUsername("oscardom");
        list3.setUsername("oscar");

        shoppingListRepository.save(Arrays.asList(list1, list2, list3));

        // Exercise
        shoppingListRepository.deleteByUsernameAndName("oscardom", "Caprabo Calafell");
        List<ShoppingList> lists = shoppingListRepository.findByUsernameAndName("oscardom", "Caprabo Calafell");

        // Assertion
        assertThat(lists.size(), is(0));


    }
}