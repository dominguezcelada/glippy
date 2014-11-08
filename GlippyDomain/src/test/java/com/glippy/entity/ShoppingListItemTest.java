package com.glippy.entity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ShoppingListItemTest {
    @Test
    public void testConstructor() {
        ShoppingListItem item1 = new ShoppingListItem("Coca-Cola","Bebida refrescante","mercadona",0.0,2);
        ShoppingListItem item2 = new ShoppingListItem("Coca-Cola",2);
        item2.setDescription("Bebida refrescante");

        assertThat(item1, is(equalTo(item2)));
    }


}