package com.glippy.scrapper;

import com.glippy.domain.ItemRepository;
import com.glippy.entity.Item;
import com.glippy.entity.Price;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ScrapTask {

    private ItemRepository itemRepository;

    public ScrapTask() {
    }

    public Item scrapItem(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        String name = doc.select(".info h2").text();
        String description = doc.select(".info h3").text();
        Elements elemPrices = doc.select(".cuanto-vale-en tr");
        ArrayList<Price> prices = new ArrayList<Price>();
        for(int i = 0; i < elemPrices.size(); i++) {
            String supermarket = elemPrices.get(i).getElementsByClass("logo-super").text();
            double price = Double.parseDouble(elemPrices.get(i).getElementsByClass("price").text().replace('€', ' ').replace(',', '.'));
            prices.add(new Price(supermarket,price));
        }
        return new Item(name,description,prices);
    }

    public ArrayList<Item> scrapCategs(String url, String selector, int level) throws IOException {
        Document doc = Jsoup.connect(url).timeout(999999999).get();
        Elements categs = doc.select(selector);
        ArrayList<Item> listItems = new ArrayList<Item>();
        for(int i = 0; i < categs.size(); i++) {
            if(level == 2) {
                listItems.addAll(scrapCategs("http://www.carritus.com" + categs.get(i).attr("href"), ".column-menu .in .item > a", level - 1));
            } else if(level == 1) {
                listItems.addAll(scrapCategs("http://www.carritus.com" + categs.get(i).attr("href"), ".content .item .image a", level - 1));
            } else {
                Item item = scrapItem("http://www.carritus.com" + categs.get(i).attr("href"));
                listItems.add(item);
            }
        }
        return listItems;
    }

    public ArrayList<String> obtainCategs(String url, String selector) throws IOException {
        Document docCateg = Jsoup.connect(url).get();
        Elements categs = docCateg.select(selector);
        ArrayList<String> subcategUrls = new ArrayList<String>();
        for(int i = 0; i < categs.size(); i++) {
           Document docSubCateg = Jsoup.connect("http://carritus.com" + categs.get(i).attr("href")).get();
           Elements subcats = docSubCateg.select(".column-menu .in .item > a");
           for(int j = 0; j < subcats.size(); j++) {
               subcategUrls.add(subcats.get(j).attr("href"));
           }
        }
        return subcategUrls;
    }
}