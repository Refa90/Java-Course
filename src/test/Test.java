package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.MyModel;
import presneter.Presenter;
import view.CLI;
import view.MyView;
import view.ViewObservable;

public class Test {
	public static void main(String[] args){
		test2();
				
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void test2(){
		InputStreamReader isr = new InputStreamReader(System.in);
		
		BufferedReader br = new BufferedReader(isr);
				
		PrintWriter	pw = new PrintWriter(System.out);
						
		MyView view = new MyView(br, pw);
		
		MyModel model = new MyModel();
		
		Presenter presenter = new Presenter(view, model);
					
		view.addObserver(presenter);
		
		model.addObserver(presenter);
		
		Object param = new Object();
		
		view.start();
	}
	
	public static void testObservables(){
		MyObservable observable = new MyObservable();
		
		MyObserver observer = new MyObserver();
		
		observable.addObserver(observer);
		
		Object param = new Object();
		
		observable.notifyObservers(param);
	}
}
