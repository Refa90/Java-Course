package presneter;

public class PropertiesManager {
	private Properties props;
	private static PropertiesManager instance;
	private static Object locker = new Object();
	
	
	
	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	private PropertiesManager(){
		
	}
	
	public static PropertiesManager getInstance(){
		if(instance == null){
			synchronized(locker){
				if(instance == null){
					instance = new PropertiesManager();
				}
			}
		}
		
		return instance;
	}
	
	
}
