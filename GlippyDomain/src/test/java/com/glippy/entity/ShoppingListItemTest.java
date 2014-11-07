package com.glippy.entity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ShoppingListItemTest {
    @Test
    public void testEquals() {
        ArrayList<Price> prices = new ArrayList<Price>();
        prices.add(new Price("mercadona",0.53));
        ShoppingListItem item1 = new ShoppingListItem(new Item("0","Coca-Cola","Bebida refrescante",prices),2);
        ShoppingListItem item2 = new ShoppingListItem(new Item("0","Coca-Cola","Bebida refrescante",prices),2);

        assertThat(item1, is(equalTo(item2)));
    }


}