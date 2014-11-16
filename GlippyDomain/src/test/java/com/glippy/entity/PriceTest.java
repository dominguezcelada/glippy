package com.glippy.entity;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PriceTest {


    // Constructors

    @Test
    public void testConstructorParam1Price() throws Exception {
        Price price1 = new Price("Mercadona",0.74);
        Price price2 = new Price(0.74);

        assertThat(price1,is(equalTo(price2)));
    }


    @Test
    public void testConstructorParam1Super() throws Exception {
        Price price1 = new Price("Mercadona",0.0);
        Price price2 = new Price("Mercadona");

        assertThat(price1,is(equalTo(price2)));
    }


    // Equals

    @Test
    public void testEquals() throws Exception {
        Price price1 = new Price ("Mercadona",3.45);
        Price price2 = new Price ("Mercadona",3.45);

        assertThat(price1,is(equalTo(price2)));
    }


}