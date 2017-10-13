package modelo;

public class WebSupport {
	WebSupport instancia = null;
	Usuario user;
	
	public WebSupport get() {
		if(instancia == null)
			instancia = new WebSupport();
		return instancia;
	}
	public WebSupport() {}
	
	
	public String getUserPanel() {
		return printUserPanel();
	}
	public String getPublicmessages() {
		String out ="";
		return out;
	}
	public String getPrivatemessages() {
		String out ="";
		return out;
	}
	
	
	private String printUserPanel() {
		return "";
	}
	private String printPublicMessage() {
		return "";
	}
	private String printPrivateMessage() {
		return "";
	}

}
