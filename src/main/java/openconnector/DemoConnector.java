package openconnector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.management.OperationsException;

import pt.iiq.custom.entity.User;
import pt.iiq.custom.service.RoleService;
import pt.iiq.custom.service.UserService;

public class DemoConnector extends AbstractConnector {

	private String urlService;
	private String userName;
	private String password;
	
	
	private void setAccessAttributes() {
		// this.urlService = this.config.getString("url");
		// this.userName = this.config.getString("user");
		// this.password = this.config.getString("password");
	}
	
	
	/**
	 * Método usado na agregação de contas e grupos
	 */
    @Override
    public Iterator<Map<String, Object>> iterate(Filter arg0) throws ConnectorException, UnsupportedOperationException {
        
    	if (Connector.OBJECT_TYPE_ACCOUNT.equals(this.objectType)) {
    		return iterateAccounts();
    	} else if (Connector.OBJECT_TYPE_GROUP.equals(this.objectType)) {
    		return iterateGroups();
    	}
    	return null;
    }
    
    private Iterator<Map<String, Object>> iterateAccounts(){

		setAccessAttributes();
		
		try {

			UserService service = new UserService();
			Map<String, Map<String, Object>> retorno = service.getAll();
			List<Map<String, Object>> listaRetorno = new ArrayList<>(retorno.values());
			
			return listaRetorno.iterator();
			
		}catch (Exception e) {
    		log.error("Collab  - ".concat(e.getMessage()));
			throw new ConnectorException(e.getMessage());
		}

	}

	private Iterator<Map<String, Object>> iterateGroups() {

		setAccessAttributes();
		
		RoleService service = new RoleService();
		
		try {
			Map<String, Map<String, Object>> retorno = service.getAll();
			List<Map<String, Object>> listaRetorno = new ArrayList<>(retorno.values());
			
			return listaRetorno.iterator();
			
		} catch (OperationsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// TODO Auto-generated method stub
			return null;
		}catch (Exception e) {
			log.error("Teste  - ".concat(e.getMessage()));
			throw new ConnectorException(e.getMessage());
		}
	}

    @Override
    public void testConnection() throws ConnectorException {
        // TODO Auto-generated method stub
    	
    	try {
    		
    	}catch (Exception e) {
			throw new ConnectorException("Erro no teste");
		}
        super.testConnection();
    }

    @Override
    public Map<String, Object> read(String arg0)
            throws ConnectorException, ObjectNotFoundException, UnsupportedOperationException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Result create(String id, List<Item> items)
            throws ConnectorException, ObjectAlreadyExistsException, UnsupportedOperationException {
        // TODO Auto-generated method stub
        return super.create(id, items);
    }

    @Override
    public Result update(String id, List<Item> items) throws ConnectorException, ObjectNotFoundException,
            IllegalArgumentException, UnsupportedOperationException {
        // TODO Auto-generated method stub
        return super.update(id, items);
    }


    
}