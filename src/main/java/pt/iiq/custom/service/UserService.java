package pt.iiq.custom.service;

import java.util.List;
import java.util.Map;

import javax.management.OperationsException;

import openconnector.ConnectorException;
import openconnector.Item;
import pt.iiq.custom.client.UserClient;
import pt.iiq.custom.entity.User;
import pt.iiq.custom.util.SailpointAccountUtils;

public class UserService extends AbstractService{

	private UserClient client;
	
	public UserService(String url, String user, String pass) {
		super(url, user, pass);
		client = new UserClient(url, user, pass);
		
	}
	
	public UserService() {
		client = new UserClient();
	}

	@Override
	public Map<String, Map<String, Object>> getAll() throws OperationsException {

		return SailpointAccountUtils.parseUserParameterToAccountMap(client.getAll());
	}

	@Override
	public Map<String, Object> getOne(String query) throws OperationsException {
		
		return SailpointAccountUtils.createAccountMap(client.getOne(new User(query)));
	}
	
	public Map<String, Object> changeStatus(String userName) throws OperationsException {
		
//		User user = client.changeStatus(new User(userName));
//		return SailpointAccountUtils.createAccountMap(user);
		return null;
	}
	
		
	public Map<String, Object> createUser(List<Item> items) {
		
		User createRequest = SailpointAccountUtils.mapUserParameterObject(items);
		
		try {
			client.createUser(createRequest);			
			
			return SailpointAccountUtils.createAccountMap(client.getOne(createRequest));
		} catch (OperationsException e) {
			throw new ConnectorException(e.getMessage());
		}
	}
	

}
