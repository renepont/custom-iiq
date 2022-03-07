package pt.iiq.custom.client;

import java.util.List;

import javax.management.OperationsException;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

import pt.iiq.custom.entity.Role;

/**
 * Classe que acede aos serviços REST
 * @author thiago.b.nascimento
 *
 */
public class RoleClient extends RestClient{

	public RoleClient(String url, String userName, String password) {
		super(url, userName, password);
	}
	
	public RoleClient() {
		super();
	}
	
	public List<Role> getAll() throws OperationsException {
		WebResource webResource = getWebResource("/role");
		
		try {
			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);
		
        
	        if(response.getStatus() == 200) {
	        	
	        	return response.getEntity(new GenericType<List<Role>>(){});
	        }else {
	        	throw new OperationsException("Erro ao tentar obter os Roles");
	        }
        
		} catch (UniformInterfaceException e) {
			
			throw new OperationsException("Erro ao tentar obter os Roles: " + e.getMessage()); 
		}
	}

	public Role getOne(String query) throws OperationsException {
		WebResource webResource = getWebResource("/role/".concat(query));
		
		try {
			ClientResponse response = webResource.accept("application/json")					
					.get(ClientResponse.class);
		
        
	        if(response.getStatus() == 200) {
	        	
	        	return response.getEntity(Role.class);
	        }else {
	        	throw new OperationsException("Erro ao tentar obter os Roles");
	        }
        
		} catch (UniformInterfaceException e) {
			
			throw new OperationsException("Erro ao tentar obter os Role: " + e.getMessage()); 
		}
	}
	
//	public List<Role> getAll() throws OperationsException {
//		WebResource webResource = getWebResource("/string");
//		
//		try {
//			ClientResponse response = webResource.accept("application/json")
//					.header("Authorization", "autenticação")
//					.get(ClientResponse.class);
//		
//        
//	        if(response.getStatus() == 200) {
//	        	
//	        	return response.getEntity(new GenericType<List<Role>>(){});
//	        }else {
//	        	throw new OperationsException("Erro ao tentar obter os Roles ");
//	        }
//        
//		} catch (InvalidKeyException | UniformInterfaceException | NoSuchAlgorithmException | NoSuchPaddingException
//				| IllegalBlockSizeException | BadPaddingException | InvalidKeySpecException e) {
//			
//			throw new OperationsException("Erro ao tentar obter os Roles: " + e.getMessage()); 
//		}
//	}


}
