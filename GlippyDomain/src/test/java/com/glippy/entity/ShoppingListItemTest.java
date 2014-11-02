package com.glippy.entity;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ShoppingListItemTest {
    @Test
    public void testEquals() {
        ShoppingListItem item1 = new ShoppingListItem("coca-cola");
        ShoppingListItem item2 = new ShoppingListItem("coca-cola");

        assertThat(item1, is(equalTo(item2)));
    }


}