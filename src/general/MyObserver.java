package general;

import java.util.Observable;
import java.util.Observer;

public interface MyObserver extends Observer {
	 
	void update(Observable o, NotificationParam param);
	
}
