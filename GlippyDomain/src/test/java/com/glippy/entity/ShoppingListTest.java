package com.glippy.entity;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ShoppingListTest {
    @Test
    private void testConstructor() {
        new ShoppingList("Mi Lista").addItem("Coca-Cola",2).addItem("Beer");
    }

    public void testEquals() {
        ShoppingList list1 = new ShoppingList("List");
        ShoppingList list2 = new ShoppingList("List");

        assertThat(list1, is(equalTo(list2)));
    }
}