package controllers;

import play.mvc.Controller;

public class Regression extends Controller {

    public static void index() {
    	String topnav="home";
        render(topnav);
    }
    
    public static void wizard() {
    	String page="wizard";
    	String topnav="home";
        render(page,topnav);
    }

}