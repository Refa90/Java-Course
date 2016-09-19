package view;

import java.util.Observable;
import java.util.Observer;

public class ViewObservable extends Observable implements Observer {
	
	@Override
	public void update(Observable o, Object arg) {
		this.setChanged();
		notifyObservers(arg);
	}
}
