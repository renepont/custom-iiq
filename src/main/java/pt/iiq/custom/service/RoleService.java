package pt.iiq.custom.service;

import java.util.List;
import java.util.Map;

import javax.management.OperationsException;

import pt.iiq.custom.client.RoleClient;
import pt.iiq.custom.entity.Role;
import pt.iiq.custom.util.SailpointAccountUtils;

public class RoleService extends AbstractService{
	
	private RoleClient client;

	public RoleService(String url, String user, String pass) {
		super(url, user, pass);
		client = new RoleClient(url, user, pass);
	}
	
	public RoleService() {
		client = new RoleClient();
	}

	@Override
	public Map<String, Map<String, Object>> getAll() throws OperationsException {
		
		return SailpointAccountUtils.parseGroupToGroupMap(client.getAll());
	}

	@Override
	public Map<String, Object> getOne(String query) throws OperationsException {
		
		return SailpointAccountUtils.parseGroupToGroupMap(getRoleByName(query));
	}
	
	private Role getRoleByName(String roleName) throws OperationsException {
		
		List<Role> roles = client.getAll();
		
		return roles.stream()
				.filter(role -> role.getName().equals(roleName))
				.findFirst().orElse(null);
	}

}
