package pt.iiq.custom.service;

public abstract class AbstractService implements Service {

	protected String url;
	protected String user;
	protected String pass;
	
	AbstractService(String url, String user, String pass) {
		this.url = url;
		this.user = user;
		this.pass = pass;
	}
	AbstractService() {
		
	}
}
