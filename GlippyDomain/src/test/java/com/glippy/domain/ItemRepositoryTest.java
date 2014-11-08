package com.glippy.domain;

import com.glippy.entity.Item;
import com.glippy.entity.ShoppingList;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/ItemRepositoryTest-context.xml")
public class ItemRepositoryTest {

    @Autowired
    ItemRepository  itemRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @After
    public void tearDown() {
        mongoTemplate.remove(new Query(), Item.class);
    }
    // Read

    @Test
    public void testFindByName() throws Exception {
        // Setup
        Item item1 = new Item("Coca-Cola").addPrice("mercadona", 0.53).addPrice("alcampo", 0.53),
                item2 = new Item("Beer").addPrice("mercadona", 0.43),
                item3 = new Item("Yogur").addPrice("carrefour",0.83).addPrice("alcampo", 0.84);

        itemRepository.save(Arrays.asList(item1, item2, item3));

        // Exercise
        List<Item> items = itemRepository.findByName("Beer");

        // Assertion
        assertThat(items.size(), is(1));
        assertThat(items.contains(item2),is(true));
    }

    // Delete

    @Test
    public void testDeleteByName() throws Exception {
        // Setup
        Item item1 = new Item("Coca-Cola").addPrice("mercadona", 0.53).addPrice("alcampo", 0.53),
                item2 = new Item("Beer").addPrice("mercadona", 0.43),
                item3 = new Item("Yogur").addPrice("carrefour",0.83).addPrice("alcampo", 0.84);

        itemRepository.save(Arrays.asList(item1, item2, item3));

        // Exercise
        itemRepository.deleteByName("Beer");
        Iterable<Item> items = itemRepository.findAll();
        Iterator<Item> it = items.iterator();
        int lenght = 0;
        while(it.hasNext()) {
            Item item = it.next();
            assertThat(item, anyOf(equalTo(item1), equalTo(item3)));
            lenght++;
        }

        // Assertion
        assertThat(lenght, is(2));
    }
}