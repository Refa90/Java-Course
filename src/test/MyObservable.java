package test;

import java.util.Observable;

public class MyObservable extends Observable {
		
	@Override
	public void notifyObservers(Object arg) {
		super.setChanged();
		super.notifyObservers(arg);
	}
}
