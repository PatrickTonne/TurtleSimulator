package util;

import java.util.Vector;

// Observer und Observable von Java Util verändern um Bug beim abspielen den Programms zu vermeiden


public class Observable {
	private Vector<Observer> observer;
	

public Observable() {
	observer = new Vector<>();
}

public synchronized void addObserver(Observer obs) {
	if (obs == null) {
		throw new NullPointerException();
	}
	if (!observer.contains(obs)) {
		observer.addElement(obs);
	}
}

public synchronized void deleteObserver(Observer obs) {
	observer.removeElement(obs);
}

public void notifyObservers() {
	//Mit Hilfe von Justin Lampe entstanden
	Object[] array;
	synchronized (this) {
			array = observer.toArray();
	}
	for (int i =0; i < array.length; i++)
		((Observer) array[i]).update();
}
}
