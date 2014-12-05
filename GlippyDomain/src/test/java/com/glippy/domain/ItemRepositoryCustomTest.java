package com.glippy.domain;

import com.glippy.entity.Item;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/repositoryDomainTest-context.xml")
public class ItemRepositoryCustomTest {

    @Autowired
    ItemRepository  itemRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @After
    public void tearDown() {
        mongoTemplate.remove(new Query(), Item.class);
    }

    @Test
    public void testFindAllTextCriteria() throws Exception {

        // Setup
        Item item1 = new Item("Coca-Cola").addPrice("mercadona", 0.53).addPrice("alcampo", 0.53),
                item2 = new Item("Beer").addPrice("mercadona", 0.43),
                item3 = new Item("Yogur").addPrice("carrefour",0.83).addPrice("alcampo", 0.84),
                item4 = new Item("COLA HACENDADO").addPrice("carrefour",0.83).addPrice("alcampo", 0.84),
                item5 = new Item("Coca-Cola Zero").addPrice("carrefour",0.83).addPrice("alcampo", 0.84);

        itemRepository.save(Arrays.asList(item1, item2, item3, item4, item5));

        // Exercise
        List<Item> items = itemRepository.findAllTextCriteria("Cola","en");

        // Assertion
        assertThat(items.size(), is(3));
        assertThat(items.contains(item1),is(true));
        assertThat(items.contains(item4),is(true));
        assertThat(items.contains(item5),is(true));

    }
}