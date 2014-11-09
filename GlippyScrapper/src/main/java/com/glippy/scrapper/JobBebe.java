package com.glippy.scrapper;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.IOException;
import java.util.ArrayList;

public class JobBebe extends QuartzJobBean {

    private ScrapTask scrapTask = new ScrapTask();

    public static String exampleSuper = "mercadona";
    public static String examplePostalCode = "08016";

    @Override
    protected void executeInternal(JobExecutionContext arg0)
            throws JobExecutionException {
        String url = "http://www.carritus.com/tienda/super/" + exampleSuper + "/cp/" + examplePostalCode + "/cm/2811";
        String selector = "#cm-bebe .cat-nivel-3 a";
        try {
            ArrayList<String> urls = scrapTask.obtainCategs(url, selector);
            System.out.println("JobBebe: " + urls.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}