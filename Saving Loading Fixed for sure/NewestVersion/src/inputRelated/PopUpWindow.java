package inputRelated;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class PopUpWindow extends JFrame {
	
	public PopUpWindow(){
		  setTitle("Simple example");
	        setSize(300, 200);
	        setLocationRelativeTo(null);
	        setLocation(0,0);
	        setDefaultCloseOperation(DISPOSE_ON_CLOSE);	
	}
	
	
	
	
	
	
	public void run(){
		this.setVisible(true);
		
	}
	
	

}
