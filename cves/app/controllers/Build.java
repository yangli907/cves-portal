package controllers;

import java.util.HashMap;
import java.util.List;

import models.build.BuildData;
import play.mvc.Controller;

public class Build extends Controller {
    public static void index(String type) throws Exception {
    	String topnav="build";
    	AbstractDataProvider dbhelper = new BuildDataProvider();
    	HashMap<String, String> filter = new HashMap<String,String>();
    	filter.put("component", type);
    	List<BuildData> buildList = dbhelper.readDataBase(filter);
        render(topnav,buildList);
    }
}