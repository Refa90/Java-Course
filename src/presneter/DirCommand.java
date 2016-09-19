package presneter;

import java.io.IOException;

import model.Model;

public class DirCommand implements Command {

	private Model model;
	
	public DirCommand(Model model){
		this.model = model;
	}
	
	@Override
	public void doCommand(Object param) {
		String[] params = (String[])param;		
		
		String[] p = new String[params.length - 1];
		System.arraycopy(params,1,p,0, params.length-1);
		
		String path = String.join(" ", p);
		try {
			model.getDirContent(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
