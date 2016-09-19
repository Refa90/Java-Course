package model;

import java.util.Observable;

public class ModelObservable extends Observable {

	@Override
	public void notifyObservers() {
		setChanged();
		notifyObservers("model");
	}
}
