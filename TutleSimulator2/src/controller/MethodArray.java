package controller;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import org.omg.CORBA.PUBLIC_MEMBER;

import model.Turtle;

public class MethodArray {

	private ArrayList<Method> methList = new ArrayList<>();
	private Method[] list;

	public MethodArray(Turtle turtle) {
		loadMethods(turtle);
	}

	void loadMethods(Turtle turtle) {

		list = turtle.getClass().getSuperclass().getDeclaredMethods();

		for (Method method : list) {
			if (Modifier.isPublic(method.getModifiers())) {

				methList.add(method);

			}
			list = turtle.getClass().getSuperclass().getDeclaredMethods();

		}
	}

	public ArrayList<Method> getMethList() {
		return methList;
	}
}
