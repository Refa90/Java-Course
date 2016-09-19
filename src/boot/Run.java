package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import gui.MazeWindow;
import model.Model;
import model.ModelObservable;
import model.MyModel;
import presneter.Presenter;
import presneter.PresenterObserver;
import view.CLI;
import view.MyView;
import view.View;
import view.ViewObservable;

public class Run {
	public static void main(String[] args){
		runGUI();		
	}
	
	public static void runCLI(){
		InputStreamReader isr = new InputStreamReader(System.in);
		
		BufferedReader br = new BufferedReader(isr);
				
		PrintWriter	pw = new PrintWriter(System.out);
						
		MyView view = new MyView(br, pw);
		
		MyModel model = new MyModel();
		
		Presenter presenter = new Presenter(view, model);
					
		view.addObserver(presenter);
		
		model.addObserver(presenter);
		
		view.start();
	}
	
	public static void runPureGUI(){
		ViewObservable observable = new ViewObservable(); 
		
		MazeWindow win = new MazeWindow(observable);
		
		win.start();
	}
	
	public static void runGUI(){
		InputStreamReader isr = new InputStreamReader(System.in);
		
		BufferedReader br = new BufferedReader(isr);
				
		PrintWriter	pw = new PrintWriter(System.out);
						
		MyView view = new MyView(br, pw);
		
		MyModel model = new MyModel();
		
		Presenter presenter = new Presenter(view, model);
					
		view.addObserver(presenter);
		
		model.addObserver(presenter);
		
		view.start();
	}
}
