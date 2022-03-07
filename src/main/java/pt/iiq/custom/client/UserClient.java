package pt.iiq.custom.client;

import java.util.List;

import javax.management.OperationsException;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

import pt.iiq.custom.entity.User;

public class UserClient extends RestClient{

	public UserClient(String url, String userName, String password) {
		super(url, userName, password);
	}
	
	
	public UserClient() {
		// TODO Auto-generated constructor stub
	}


	public User changeStatus(User user, String status) throws OperationsException{
		
		
		WebResource webResource = getWebResource("/user/".concat(String.valueOf(user.getId()).concat("/").concat(status)));
		
		try {
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.put(ClientResponse.class);
			
			User userResponse = response.getEntity(User.class);
			
			if(response.getStatus() == 200) {
				return userResponse;
			}else {
				throw new OperationsException("Erro ao tentar mudar o utilizador");
			}
		} catch (UniformInterfaceException e) {
			throw new OperationsException("Erro ao tentar mudar o utilizador: " + e.getMessage());
		}
		
	}

	public List<User> getAll() throws OperationsException {
		
		WebResource webResource = getWebResource("/user");
		
		try {
			ClientResponse response = webResource.accept("application/json")					
					.get(ClientResponse.class);
		
	        if(response.getStatus() == 200) {
	        	return response.getEntity(new GenericType<List<User>>(){});
	        }else {
	        	throw new OperationsException("Erro ao tentar obter os utilizadores do OneContact");
	        }
        
		} catch (UniformInterfaceException e) {
			
			throw new OperationsException("Erro ao tentar obter os utilizadores do OneContact: " + e.getMessage()); 
		}
	}

	public User getOne(User user) throws OperationsException {
		WebResource webResource = getWebResource("/user/".concat(String.valueOf(user.getId())));
		
		try {
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.get(ClientResponse.class);
        
	        if(response.getStatus() == 200) {
	        	return response.getEntity(User.class);
	        }else {
	        	throw new OperationsException("Erro ao tentar obter o utilizador ".concat(user.getUserName()));
	        }
		} catch (UniformInterfaceException e) {
			throw new OperationsException("Erro ao tentar obter o utilizador: " + e.getMessage());
		}
	}
	
	public User createUser(User user) throws OperationsException  {
    	WebResource webResource = getWebResource("/users");
    	
    	try {

    		ClientResponse response = webResource.accept("application/json").type("application/json")
					.post(ClientResponse.class,user.objectToJsonString());
	        
	
    		if(response.getStatus() == 200) {

    			User userResponse = response.getEntity(User.class);
        	
	        	return userResponse;
	        }else {
	        	throw new UnsupportedOperationException("Failed : HTTP error code : "
						+ response.getStatus());
	        }
	
    	} catch (UniformInterfaceException e) {
			throw new OperationsException("Erro ao tentar obter o utilizador: " + e.getMessage());
		}
	}
	
	public User changeUserRole(User user) throws OperationsException  {
		WebResource webResource = getWebResource("/users");
		
		try {
			
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.put(ClientResponse.class,user.objectToJsonString());
			
			
			if(response.getStatus() == 200) {
				
				User userResponse = response.getEntity(User.class);
				
				return userResponse;
			}else {
				throw new UnsupportedOperationException("Failed : HTTP error code : "
						+ response.getStatus());
			}
			
		} catch (UniformInterfaceException e) {
			throw new OperationsException("Erro ao tentar obter o utilizador: " + e.getMessage());
		}
	}

}
