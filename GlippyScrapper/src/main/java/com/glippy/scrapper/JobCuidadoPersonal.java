package com.glippy.scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.IOException;
import java.util.ArrayList;

public class JobCuidadoPersonal extends QuartzJobBean {

    private ScrapTask scrapTask = new ScrapTask();

    public static String exampleSuper = "mercadona";
    public static String examplePostalCode = "08016";

    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        String url = "http://www.carritus.com/tienda/super/" + exampleSuper + "/cp/" + examplePostalCode + "/cm/2811";
        String selector = "#cm-cuidado-personal .cat-nivel-3 a";
        try {
            ArrayList<String> urls = scrapTask.obtainCategs(url, selector);
            context.getJobDetail().getJobDataMap().put("urls",urls);
            System.out.println("JobAlimentacion: " + urls.size() + " " + context.getJobDetail().getJobDataMap().get("urls"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}