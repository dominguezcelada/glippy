package com.glippy.scrapper;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.IOException;

public class RunMeJob extends QuartzJobBean {
    private RunMeTask runMeTask;

    public static String exampleSuper = "mercadona";
    public static String examplePostalCode = "08016";

    public void setRunMeTask(RunMeTask runMeTask) {
        this.runMeTask = runMeTask;
    }

    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {

        try {
            runMeTask.scrapCategs("http://www.carritus.com/tienda/super/" + examplePostalCode + "/cp/" + exampleSuper + "/cm/1529", ".cat-nivel-3 a", 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}