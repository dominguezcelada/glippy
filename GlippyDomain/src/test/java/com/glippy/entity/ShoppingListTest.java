package com.glippy.entity;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ShoppingListTest {
    @Test
    public void testConstructor() {
        ShoppingList list = new ShoppingList("Mi Lista");

        assertThat(list.getName(), is(equalTo("Mi Lista")));
    }

    @Test
    public void testAddSyntax() {
        ShoppingListItem item1 = new ShoppingListItem("Beer", 1),
                         item2 = new ShoppingListItem("Coca-Cola", 2);

        ShoppingList list = new ShoppingList("Mi Lista").addItem(item1).addItem(item2);

        assertThat(list.getItems().contains(item1), is(true));
        assertThat(list.getItems().contains(item2), is(true));
    }

    @Test
    public void testEquals() {
        ShoppingList list1 = new ShoppingList("List");
        ShoppingList list2 = new ShoppingList("List");

        assertThat(list1, is(equalTo(list2)));
    }
}