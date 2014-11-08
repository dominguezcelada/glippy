package com.glippy.entity;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ShoppingListTest {

    @Test
    public void testSetName() throws Exception {
        ShoppingList list1 = new ShoppingList("List");
        ShoppingList list2 = new ShoppingList();
        list2.setName("List");

        assertThat(list1, is(equalTo(list2)));
    }

    @Test
    public void testSetUsername() throws Exception {
        ShoppingList list1 = new ShoppingList("List","oscardom");
        ShoppingList list2 = new ShoppingList("List");
        list2.setUsername("oscardom");

        assertThat(list1,is(equalTo(list2)));
    }

    @Test
    public void testEquals() throws Exception {
        ShoppingList list2 = new ShoppingList("List",new ShoppingListItem("Beer","mercadona",0.45,2));
        ShoppingList list1 = new ShoppingList("List","Beer",0.45,"mercadona",2);


        assertThat(list1,is(equalTo(list2)));
    }

    @Test
    public void testAddItem() throws Exception {
        ShoppingListItem item1 = new ShoppingListItem("Beer","mercadona",0.45,2);
        ShoppingListItem item2 = new ShoppingListItem("Coca-Cola");
        ShoppingList list1 = new ShoppingList("List",Arrays.asList(item1,item2));
        ShoppingList list2 = new ShoppingList("List");

        list2.addItem("Beer",0.45,"mercadona",2);
        list2.addItem("Coca-Cola");

        assertThat(list1,is(equalTo(list2)));
    }
}