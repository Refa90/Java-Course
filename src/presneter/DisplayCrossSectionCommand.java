package presneter;

import model.Model;

public class DisplayCrossSectionCommand implements Command{

	private Model model;
	
	public DisplayCrossSectionCommand (Model model){
		this.model = model;
	}
	
	@Override
	public void doCommand(Object param) {
		String[] params = (String[])param;
		
		String axis = params[1];
		int value = Integer.parseInt(params[2]);
		String mazeName = params[3];
				
		model.displayCrossSection(axis, value, mazeName);
		
	}

}
