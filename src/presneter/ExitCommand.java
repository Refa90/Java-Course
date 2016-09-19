package presneter;
import model.Model;


public class ExitCommand implements Command{
	
	private Model model;
	
	public ExitCommand(Model model){
		this.model = model;
	}
	
	@Override
	public void doCommand(Object param) throws Exception{
		model.terminate();
	}
}
