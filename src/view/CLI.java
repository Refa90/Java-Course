package view;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import general.NotificationParam;
import general.Startable;
import general.ThreadsManager;
import presneter.Command;

public class CLI extends Observable implements Startable {
	
	BufferedReader in;
	PrintWriter out;
	
	private Map<String,Command> map;
	private static Scanner scanner;
	
	public CLI(BufferedReader in, PrintWriter out){
		this.in = in;
		this.out = out;
	}
	
	@Override
	public void start(){
		run();
	}

	public void run() {
					
		Thread th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				String exit = "exit";
				String input = null;
				scanner = new Scanner(in);
								
				do{
					out.write("\r\n");
					out.write("please enter command");
					out.write("\r\n");
					
					out.flush();
					
					input = scanner.nextLine();
					
					String[] values = input.split(" ");
					
					NotificationParam param = new NotificationParam(values, values[0]);
															
					notifyObservers(param);
				}
				while(!input.equals(exit));
				
			}
		});
		
		ThreadsManager.getInstance().RegisterRunnable(th);
	}
	
	public void display(String content){
		out.write(content);
		out.flush();
	}
	
	public void setCommands(Map<String,Command> map){
		this.map = map;
	}
	
	public void terminate(){
		scanner.close();
	}
	
	@Override
	public void notifyObservers(Object arg){
		super.setChanged();
		super.notifyObservers(arg);
	}
}
