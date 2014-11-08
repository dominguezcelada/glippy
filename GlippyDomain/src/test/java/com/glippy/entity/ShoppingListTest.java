package com.glippy.entity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ShoppingListTest {

    // Constructors


    @Test
    public void testConstructor1Param() throws Exception {
        ShoppingList list1 = new ShoppingList("List",new ArrayList<ShoppingListItem>());
        ShoppingList list2 = new ShoppingList("List");

        assertThat(list1,is(equalTo(list2)));
    }

    @Test
    public void testConstructor2Param() throws Exception {
        ShoppingList list1 = new ShoppingList("List",new ArrayList<ShoppingListItem>(Arrays.asList(new ShoppingListItem("Beer"))));
        ShoppingList list2 = new ShoppingList("List",new ShoppingListItem("Beer"));

        assertThat(list1,is(equalTo(list2)));
    }

    @Test
    public void testConstructor2ParamAuthor() throws Exception {
        ShoppingList list1 = new ShoppingList("List","oscardom");
        ShoppingList list2 = new ShoppingList();
        list2.setName("List");
        list2.setUsername("oscardom");

        assertThat(list1,is(equalTo(list2)));
    }

    @Test
    public void testConstructor4Param() throws Exception {
        ShoppingList list1 = new ShoppingList("List",new ArrayList<ShoppingListItem>(Arrays.asList(new ShoppingListItem("Beer",2,"mercadona",0.53))));
        ShoppingList list2 = new ShoppingList("List","Beer","mercadona",2,0.53);

        assertThat(list1,is(equalTo(list2)));
    }

    // Equals

    @Test
    public void testEquals() throws Exception {
        ShoppingList list2 = new ShoppingList("List",new ShoppingListItem("Beer","mercadona",0.45,2));
        ShoppingList list1 = new ShoppingList("List","Beer",0.45,"mercadona",2);


        assertThat(list1,is(equalTo(list2)));
    }

    // Add Items

    @Test
    public void testAddItem1Param() throws Exception {
        ShoppingListItem item1 = new ShoppingListItem("Coca-Cola");
        ShoppingListItem item2 = new ShoppingListItem("Beer",1);
        ShoppingList list1 = new ShoppingList("List",Arrays.asList(item1,item2));
        ShoppingList list2 = new ShoppingList("List");

        list2.addItem("Coca-Cola");
        list2.addItem("Beer");

        assertThat(list1,is(equalTo(list2)));
    }

    @Test
    public void testAddItem2ParamQuantity() throws Exception {
        ShoppingListItem item1 = new ShoppingListItem("Coca-Cola",2);
        ShoppingListItem item2 = new ShoppingListItem("Beer",3);
        ShoppingList list1 = new ShoppingList("List",Arrays.asList(item1,item2));
        ShoppingList list2 = new ShoppingList("List");

        list2.addItem("Coca-Cola",2);
        list2.addItem("Beer",3);

        assertThat(list1,is(equalTo(list2)));
    }

    @Test
    public void testAddItem2ParamPrice() throws Exception {
        ShoppingListItem item1 = new ShoppingListItem("Coca-Cola",0.53);
        ShoppingListItem item2 = new ShoppingListItem("Beer",0.46);
        ShoppingList list1 = new ShoppingList("List",Arrays.asList(item1,item2));
        ShoppingList list2 = new ShoppingList("List");

        list2.addItem("Coca-Cola",0.53);
        list2.addItem("Beer",0.46);

        assertThat(list1,is(equalTo(list2)));
    }

    @Test
    public void testAddItem3Param() throws Exception {
        ShoppingListItem item1 = new ShoppingListItem("Coca-Cola",0.53,"mercadona");
        ShoppingListItem item2 = new ShoppingListItem("Beer",0.46,"alcampo");
        ShoppingList list1 = new ShoppingList("List",Arrays.asList(item1,item2));
        ShoppingList list2 = new ShoppingList("List");

        list2.addItem("Coca-Cola",0.53,"mercadona");
        list2.addItem("Beer",0.46,"alcampo");

        assertThat(list1,is(equalTo(list2)));
    }

    @Test
    public void testAddItem4Param() throws Exception {
        ShoppingListItem item1 = new ShoppingListItem("Coca-Cola",0.53,"mercadona",3);
        ShoppingListItem item2 = new ShoppingListItem("Beer",0.46,"alcampo",1);
        ShoppingList list1 = new ShoppingList("List",Arrays.asList(item1,item2));
        ShoppingList list2 = new ShoppingList("List");

        list2.addItem("Coca-Cola",0.53,"mercadona",3);
        list2.addItem("Beer",0.46,"alcampo",1);

        assertThat(list1,is(equalTo(list2)));
    }


}