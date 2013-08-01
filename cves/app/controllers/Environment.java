package controllers;

import java.util.List;

import models.environment.EnvironmentData;
import play.mvc.Controller;

public class Environment extends Controller {

    public static void index() throws Exception {
    	String topnav = "env";
    	AbstractDataProvider dbhelper = new EnvironmentDataProvider();
    	List<EnvironmentData> envList = dbhelper.readDataBase(null);
        render(topnav,envList);
    }

}