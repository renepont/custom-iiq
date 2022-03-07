package pt.iiq.custom.service;

import static org.junit.jupiter.api.Assertions.*;

import javax.management.OperationsException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class UserServiceTest {

	private static UserService service;
	
	@BeforeAll
	static void init() {
		service = new UserService();
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

}
