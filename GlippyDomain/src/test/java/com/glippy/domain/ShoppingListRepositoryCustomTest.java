package com.glippy.domain;

import com.glippy.entity.Item;
import com.glippy.entity.ShoppingList;
import com.glippy.entity.ShoppingListItem;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/repositoryDomainTest-context.xml")
public class ShoppingListRepositoryCustomTest {

    @Autowired
    ShoppingListRepository  shoppingListRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @After
    public void tearDown() {
        mongoTemplate.remove(new Query(), ShoppingList.class);
    }

    @Test
    public void testUpdateShoppingList() throws Exception {
        // Setup
        ShoppingList list1 = new ShoppingList("Caprabo").addItem("Coca-Cola",2).addItem("Beer"),
                list2 = new ShoppingList("Caprabo a casa").addItem("Coca-Cola").addItem("Beer", 2),
                list3 = new ShoppingList("Another One").addItem("Beer", 2);

        shoppingListRepository.save(Arrays.asList(list1, list2, list3));

        // Exercise
        Query querySelect = new Query()
                .addCriteria(Criteria.where("name").is("Caprabo"));
        Update queryUpdate = new Update().set("name", "Lista Carrefour");
        shoppingListRepository.updateShoppingList(querySelect, queryUpdate);

        // Assertion
        List<ShoppingList> lists = shoppingListRepository.findByName("Caprabo");
        List<ShoppingList> newLists = shoppingListRepository.findByName("Lista Carrefour");

        assertThat(lists.size(), is(0));
        assertThat(newLists.size(), is(1));
        assertThat(newLists.get(0).getName(), is(equalTo("Lista Carrefour")));
    }

    @Test
    public void testAddItem() throws Exception {

        ShoppingList list1 = new ShoppingList("Caprabo").addItem("Coca-Cola",2).addItem("Beer"),
                list2 = new ShoppingList("Caprabo a casa").addItem("Coca-Cola").addItem("Beer", 2),
                list3 = new ShoppingList("Another One").addItem("Beer", 2);

        shoppingListRepository.save(Arrays.asList(list1, list2, list3));

        // Exercise
        Item item = new Item();
        item.setName("Producto de prueba");

        Query querySelect = new Query()
                .addCriteria(Criteria.where("name").is("Another One"));
        ShoppingListItem shoppingListItem = new ShoppingListItem(item);
        Update queryUpdate = new Update().push("listItems", shoppingListItem);
        shoppingListRepository.addItem(querySelect, queryUpdate);

        // Assertion
        List<ShoppingList> lists = shoppingListRepository.findByName("Another One");

        assertThat(lists.size(), is(1));
        assertThat(lists.get(0).getName(), is(equalTo("Another One")));
        assertThat(lists.get(0).getListItems().contains(shoppingListItem), is(true));
        assertThat(lists.get(0).getListItems().size(), is(2));

    }

    @Test
    public void testRemoveItem() throws Exception {

        ShoppingList list1 = new ShoppingList("Caprabo").addItem("Coca-Cola",2).addItem("Beer"),
                list2 = new ShoppingList("Caprabo a casa").addItem("Coca-Cola").addItem("Beer", 2),
                list3 = new ShoppingList("Another One").addItem("Beer", 2);

        shoppingListRepository.save(Arrays.asList(list1, list2, list3));

        // Exercise

        Query querySelect = new Query()
                .addCriteria(Criteria.where("name").is("Caprabo"));
        Update queryUpdate = new Update().pull("listItems", new Query().addCriteria(Criteria.where("item.name").is("Coca-Cola")));
        shoppingListRepository.removeItem(querySelect, queryUpdate);

        // Assertion
        List<ShoppingList> lists = shoppingListRepository.findByName("Caprabo");

        assertThat(lists.size(), is(1));
        assertThat(lists.get(0).getName(), is(equalTo("Caprabo")));
        assertThat(lists.get(0).getListItems().get(0).getItem().getName(), is(equalTo("Beer")));
        assertThat(lists.get(0).getListItems().size(), is(1));

    }

    @Test
    public void testFindCustom() throws Exception {

        ShoppingList list1 = new ShoppingList("Caprabo").addItem("Coca-Cola",2).addItem("Beer"),
                list2 = new ShoppingList("Caprabo a casa").addItem("Coca-Cola").addItem("Beer", 2),
                list3 = new ShoppingList("Another One").addItem("Beer", 2);

        shoppingListRepository.save(Arrays.asList(list1, list2, list3));

        // Exercise

        Query querySelect = new Query()
                .with(new Sort(new Sort.Order(Sort.Direction.DESC, "createdDate")));

        // Assertion
        List<ShoppingList> lists = shoppingListRepository.findCustom(querySelect);

        assertThat(lists.size(), is(3));
        assertThat(lists.get(2).getName(), is(equalTo("Caprabo")));
        assertThat(lists.get(1).getName(), is(equalTo("Caprabo a casa")));
        assertThat(lists.get(0).getName(), is(equalTo("Another One")));

    }
}