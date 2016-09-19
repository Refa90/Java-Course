package presneter;

import model.Model;

public class DisplaySolutionCommand implements Command {
	
	private Model model;
	
	public DisplaySolutionCommand (Model model){
		this.model = model;
	}
	
	@Override public void doCommand(Object param) throws Exception{
		String[] params = (String[])param;
		
		String mazeName = params[1];
		
		
		model.displaySolution(mazeName);
	}
}
