package com.glippy.entity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ShoppingListItemTest {


    // Constructors

    @Test
    public void testConstructor1Param() {
        ShoppingListItem item2 = new ShoppingListItem("Coca-Cola");
        ShoppingListItem item1 = new ShoppingListItem("Coca-Cola","Mercadona",0.0,1);

        assertThat(item1, is(equalTo(item2)));
    }

    @Test
    public void testConstructor2ParamQuantity() {
        ShoppingListItem item2 = new ShoppingListItem("Coca-Cola",2);
        ShoppingListItem item1 = new ShoppingListItem("Coca-Cola","Mercadona",0.0,2);

        assertThat(item1, is(equalTo(item2)));
    }

    @Test
    public void testConstructor2ParamPrice() {
        ShoppingListItem item2 = new ShoppingListItem("Coca-Cola",0.53);
        ShoppingListItem item1 = new ShoppingListItem("Coca-Cola","Mercadona",0.53,1);

        assertThat(item1, is(equalTo(item2)));
    }

    @Test
    public void testConstructor3ParamPrice() {
        ShoppingListItem item2 = new ShoppingListItem("Coca-Cola",0.53,"Alcampo");
        ShoppingListItem item1 = new ShoppingListItem("Coca-Cola","Alcampo",0.53,1);

        assertThat(item1, is(equalTo(item2)));
    }

    @Test
    public void testConstructor4Param1() {
        ShoppingListItem item2 = new ShoppingListItem("Coca-Cola",0.53,"Alcampo",8);
        ShoppingListItem item1 = new ShoppingListItem("Coca-Cola","Alcampo",0.53,8);

        assertThat(item1, is(equalTo(item2)));
    }


    @Test
    public void testConstructor4Param3() {
        ShoppingListItem item2 = new ShoppingListItem("Coca-Cola",8,"Alcampo",0.53);
        ShoppingListItem item1 = new ShoppingListItem("Coca-Cola","Alcampo",0.53,8);

        assertThat(item1, is(equalTo(item2)));
    }
}
