package general;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import presneter.PropertiesManager;

public class ThreadsManager {
	private static ThreadsManager instance;
	private static Object locker = new Object();
	private ExecutorService executor;
	
	public static ThreadsManager getInstance(){
		if(instance == null){
			synchronized (locker) {
				if(instance == null){
					instance = new ThreadsManager();
				}
			}
		}
		
		return instance;
	}
	
	private ThreadsManager(){
		executor = Executors.newFixedThreadPool(PropertiesManager.getInstance().getProps().getNumOfThreads());
	}
		
	public <T> Future<T> RegisterCallable(Callable<T> callable){
		Future<T> result = null;
		
		result = executor.submit(callable);
		
		return result;
	}
	
	public void RegisterRunnable(Runnable runnable){
		this.executor.submit(runnable);
	}
	
	//TODO - fix!
	public void terminate(){
		
	}
}
