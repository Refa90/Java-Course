package gui;

import java.util.PriorityQueue;
import java.util.Queue;

public class NotificationQueue<T> {
	private static NotificationQueue instance;
	private static Object locker = new Object();
	
	private Queue<T> queue;
	
	private NotificationQueue (){
		queue = new PriorityQueue<T>();
	}
	
	public static<T> NotificationQueue<T> GetInstance(){
		if(instance == null){
			synchronized(locker){
				if(instance == null){
					instance = new NotificationQueue<T>();
				}
			}
		}
		
		return instance;
	}
	
	public void add(T notification){
		queue.add(notification);
	}
	
	public T poll(){
		T notification = queue.poll();
		
		return notification;
	}
}
