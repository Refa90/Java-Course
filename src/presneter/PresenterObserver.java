package presneter;

import java.util.Observable;
import java.util.Observer;

public class PresenterObserver implements Observer {
	
	@Override
	public void update(Observable obs, Object arg) {
		System.out.println(arg);
	}
}
