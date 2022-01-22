package dbase;

public class Utility extends Exception{
	
	 public Utility(String message) {
	        super(message);
	        //JOptionPane.showMessageDialog(null, message, "Informations", JOptionPane.PLAIN_MESSAGE);
	        Tools t=new Tools();
	        t.alertmsgbox(message);
	    }
	    
	    public Utility(Throwable cause) {
	        super(cause);
	    }
	    
	    public Utility(String message, Throwable cause) {
	        super(message, cause);
	    }

}
