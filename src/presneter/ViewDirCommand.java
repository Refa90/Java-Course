package presneter;

import java.io.File;
import view.View;

public class ViewDirCommand implements Command {

	private View view;
	
	public ViewDirCommand(View view){
		this.view = view;
	}
	
	@Override
	public void doCommand(Object param) throws Exception {
		File[] files = (File[])param;
		
		view.displayDirContent(files);
	}

}
