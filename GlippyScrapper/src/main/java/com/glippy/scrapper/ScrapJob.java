package com.glippy.scrapper;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.IOException;
import java.util.ArrayList;

public class ScrapJob extends QuartzJobBean {
    private ScrapTask scrapTask;

    public static String exampleSuper = "mercadona";
    public static String examplePostalCode = "08016";

    public void setScrapTask(ScrapTask scrapTask) {
        this.scrapTask = scrapTask;
    }

    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {

        try {
//            scrapTask.scrapCategs("http://www.carritus.com/tienda/super/" + examplePostalCode + "/cp/" + exampleSuper + "/cm/1529", ".cat-nivel-3 a", 2);
              ArrayList<String> urls = scrapTask.obtainCategs("http://www.carritus.com/tienda/super/" + exampleSuper + "/cp/" + examplePostalCode + "/cm/2811");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}