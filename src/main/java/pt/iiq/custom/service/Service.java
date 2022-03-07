package pt.iiq.custom.service;

import java.util.Map;

import javax.management.OperationsException;

public interface Service {

	
	public Map<String, Map<String, Object>> getAll() throws OperationsException;
	
	public Map<String, Object> getOne(String query) throws OperationsException;
}
