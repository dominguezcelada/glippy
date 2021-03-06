package com.glippy.entity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ItemTest {

    // Constructors

    @Test
    public void testConstructorParam1() throws Exception {
        Item item1 = new Item("Beer");
        Item item2 = new Item("Beer", new ArrayList<Price>(Arrays.asList(new Price("Mercadona",0.0))));

        assertThat(item1,is(equalTo(item2)));
    }

    @Test
    public void testConstructorParam2() throws Exception {
        Item item1 = new Item("Beer",new Price("Mercadona",0.53));
        Item item2 = new Item("Beer", new ArrayList<Price>(Arrays.asList(new Price("Mercadona",0.53))));

        assertThat(item1,is(equalTo(item2)));
    }

    @Test
    public void testConstructorParam3() throws Exception {
        Item item1 = new Item("Beer","Alcampo",0.53);
        Item item2 = new Item("Beer", new ArrayList<Price>(Arrays.asList(new Price("Alcampo",0.53))));

        assertThat(item1,is(equalTo(item2)));
    }

    @Test
    public void testConstructorParam3v2() throws Exception {
        Item item1 = new Item("Beer",0.53,"Alcampo");
        Item item2 = new Item("Beer", new ArrayList<Price>(Arrays.asList(new Price("Alcampo",0.53))));

        assertThat(item1,is(equalTo(item2)));
    }

    // Add Price

    @Test
    public void testAddPrice() throws Exception {
        Item item1 = new Item("Beer", new ArrayList<Price>(Arrays.asList(new Price("Mercadona",0.44),new Price("Alcampo",0.33))));
        Item item2 = new Item();
        item2.setName("Beer");
        item2.addPrice("Mercadona",0.44);
        item2.addPrice("Alcampo",0.33);

        assertThat(item1,is(equalTo(item2)));
    }
}