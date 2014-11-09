package com.glippy.scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.IOException;
import java.util.ArrayList;

public class JobFrescos extends QuartzJobBean {

    public static String exampleSuper = "mercadona";
    public static String examplePostalCode = "08016";

    @Override
    protected void executeInternal(JobExecutionContext arg0)
            throws JobExecutionException {
        String url = "http://www.carritus.com/tienda/super/" + exampleSuper + "/cp/" + examplePostalCode + "/cm/2811";
        String selector = "#cm-frescos .cat-nivel-3 a";
        try {
            ArrayList<String> urls = obtainCategs(url, selector);
            System.out.println("JobFrescos: " + urls.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> obtainCategs(String url, String selector) throws IOException {
        ArrayList<String> categUrls = new ArrayList<String>();
        Document doc = Jsoup.connect(url).get();
        Elements categs = doc.select(selector);
        for(int i = 0; i < categs.size(); i++) {
            categUrls.add(categs.get(i).attr("href"));
        }
        return categUrls;
    }

}