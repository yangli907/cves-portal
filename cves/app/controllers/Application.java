package controllers;

import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {
    	String topnav="home";
        render(topnav);
    }

}