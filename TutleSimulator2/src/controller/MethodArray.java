package controller;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import org.omg.CORBA.PUBLIC_MEMBER;

import model.Turtle;
import util.Invisible;

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
		}

		list = turtle.getClass().getDeclaredMethods();
		for (Method method : list) {
			if(!Modifier.isAbstract(method.getModifiers()) && !Modifier.isStatic(method.getModifiers()) && !Modifier.isPrivate(method.getModifiers()) && (method.getAnnotation(Invisible.class) == null)) {
				methList.add(method);
			}

		}
	}

	public ArrayList<Method> getMethList() {
		return methList;
	}
}
