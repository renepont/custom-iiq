package pt.iiq.custom.service;

import static org.junit.jupiter.api.Assertions.*;

import javax.management.OperationsException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RoleServiceTest {

	private static RoleService service;
	
	@BeforeAll
	static void init() {
		service = new RoleService();
	}
	
	
	@Test
	void testGetAll() {
		try {
			assertNotNull(service.getAll());
		} catch (OperationsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testGetOne() {
		try {
			assertNotNull(service.getAll());
		} catch (OperationsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
