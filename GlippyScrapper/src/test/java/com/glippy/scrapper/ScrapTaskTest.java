package com.glippy.scrapper;

import com.glippy.entity.Item;
import com.glippy.entity.Price;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ScrapTaskTest {

    public static String exampleSuper = "mercadona";
    public static String examplePostalCode = "08016";

    @Test
    public void testScrapItem() throws Exception {
        ScrapTask task = new ScrapTask();
        Price price1 = new Price("Mercadona",1.70),
                price2 = new Price("El Corte Inglés",1.57),
                price3 = new Price("Carrefour",1.70),
                price4 = new Price("Hipercor",1.57),
                price5 = new Price("Alcampo",1.52);
        ArrayList<Price> originalPrices = new ArrayList<Price>(Arrays.asList(price1, price2, price3, price4, price5));

        Item originalItem = new Item("Hero","Confitura Cereza, Hero, Tarro 345 G", originalPrices);
        Item extractedItem = task.scrapItem("http://www.carritus.com/tienda/super/" + exampleSuper + "/cp/" + examplePostalCode + "/producto/29552058");

        assertThat(originalItem.getPrices().size(), is(5));
        assertThat(extractedItem.getPrices().size(), is(5));
        assertThat(originalItem, is(equalTo(extractedItem)));
    }

    @Test
    public void testScrapCategsLastLevel() throws IOException {
        ScrapTask task = new ScrapTask();
        ArrayList<Item> listExtractedItems = task.scrapCategs("http://www.carritus.com/tienda/super/" + exampleSuper + "/cp/" + examplePostalCode + "/cm/1972", ".content .item .image a", 0);
        assertThat(listExtractedItems.size(),is(5));
        assertThat(listExtractedItems.get(0).getName(),is("Roldan"));
        assertThat(listExtractedItems.get(0).getDescription(),is("Aceituna Aloreña (malagueña) Verdes, Partidas Y Aliñadas (tapa Amarilla), Roldan, Tarro 1440 G Escurrido 800 G"));
    }

    @Test
    public void testScrapCategsLevel1() throws IOException {
        ScrapTask task = new ScrapTask();
        ArrayList<Item> listExtractedItems = task.scrapCategs("http://www.carritus.com/tienda/super/" + exampleSuper + "/cp/" + examplePostalCode + "/cm/2530", ".column-menu .in .item > a", 1);
        assertThat(listExtractedItems.size(),is(19));
    }

    @Test
    public void testScrapCategsLevel2() throws IOException {
        ScrapTask task = new ScrapTask();
        ArrayList<Item> listExtractedItems = task.scrapCategs("http://www.carritus.com/tienda/super/" + exampleSuper + "/cp/" + examplePostalCode + "/cm/2493", ".cat-nivel-3 a", 2);
        assertThat(listExtractedItems.size(),is(297));
    }
}