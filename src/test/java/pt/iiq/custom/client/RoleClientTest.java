package pt.iiq.custom.client;

import static org.junit.jupiter.api.Assertions.*;

import javax.management.OperationsException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RoleClientTest {

	private static RoleClient client;
	@BeforeAll
	public static void init() {
		client = new RoleClient();
	}
	
	@Test
	void testGetAll() throws OperationsException {
		assertNotNull(client.getAll());
	}

	@Test
	void testGetOne() throws OperationsException {
		assertNotNull(client.getOne("1"));
	}

}
