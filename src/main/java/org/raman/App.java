package org.raman;

import org.apache.logging.log4j.Logger;
import org.raman.abstractRes.ExcelFileReader;
import org.raman.api.monitoring.CheckApiStat;
import org.apache.logging.log4j.LogManager;


import java.util.List;



public class App 
{
    protected static final Logger logger = LogManager.getLogger();
    public static void main( String[] args )
    {
        List<String> apis = new ExcelFileReader().getListOfApis(); //extract list of all the apis
        List<String> failedApis =   new CheckApiStat().getListOfFailedApis(apis); //getFailedApis
        if(!failedApis.isEmpty())
        {
            new MailSender().reportFailedApis(failedApis);
        }
        else {
            logger.info("No failures detected");
        }


    }
}
