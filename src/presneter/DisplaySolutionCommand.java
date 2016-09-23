package presneter;

import model.Model;

public class DisplaySolutionCommand implements Command {
	
	private Model model;
	
	public DisplaySolutionCommand (Model model){
		this.model = model;
	}
	
	@Override public void doCommand(Object param) throws Exception{
		String mazeName = param.toString();
		
		model.displaySolution(mazeName);
	}
}
