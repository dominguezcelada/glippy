package com.glippy.scrapper;

import com.glippy.entity.Item;
import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.IOException;
import java.util.ArrayList;

public class JobCategories extends QuartzJobBean {

    private ScrapTask scrapTask = new ScrapTask();

    public static String exampleSuper = "mercadona";
    public static String examplePostalCode = "08016";

    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        /*String url = "http://www.carritus.com/tienda/super/" + exampleSuper + "/cp/" + examplePostalCode + "/cm/2811";
        DateTime dateTime = new DateTime(context.getFireTime().getTime());

        String key;
        switch (dateTime.getSecondOfMinute()) {

            case 12:
                key = "animales";
                break;
            case 18:
                key = "bebe";
                break;
            case 24:
                key = "congelados";
                break;
            case 30:
                key = "cuidado";
                break;
            case 36:
                key = "dieteticos";
                break;
            case 42:
                key = "frescos";
                break;
            case 48:
                key = "hogar";
                break;
            case 54:
                key = "preparados";
                break;
            default:
                key = "alimentacion";
                break;
        }*/
//        String selector = (String) context.getJobDetail().getJobDataMap().get(key);
        try {
            // Colas
            ArrayList<Item> items = scrapTask.scrapCategs("http://www.carritus.com/tienda/super/" + exampleSuper + "/cp/" + examplePostalCode + "/cm/1731", ".content .item .image a", 0);

            // Fanta Naranja
            items = scrapTask.scrapCategs("http://www.carritus.com/tienda/super/" + exampleSuper + "/cp/" + examplePostalCode + "/cm/1735", ".content .item .image a", 0);

            // Aguas
            items = scrapTask.scrapCategs("http://www.carritus.com/tienda/super/" + exampleSuper + "/cp/" + examplePostalCode + "/cm/2752", ".content .item .image a", 0);

//            context.getJobDetail().getJobDataMap().put(key,urls);
//            System.out.println(key + ": " + urls.size() + " " + context.getJobDetail().getJobDataMap().get(key));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}