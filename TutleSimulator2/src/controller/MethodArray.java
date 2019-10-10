package controller;

import java.lang.reflect.Method;
import java.util.ArrayList;

import model.Turtle;

public class MethodArray {

	public Method[] list;
	
	public MethodArray(Turtle turtle) {
		loadMethods(turtle);
	}
 void loadMethods(Turtle turtle) {
	 
	 list = turtle.getClass().getMethods();
	 
 }
}
