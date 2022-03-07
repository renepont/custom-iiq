package openconnector;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DemoConnectorTest {

	private static DemoConnector connector;
	
	@BeforeAll
	static void init() {
		connector = new DemoConnector();
		
		connector.setObjectType(Connector.OBJECT_TYPE_GROUP);
	}
	
	@Test
	void testIterateFilter() {
		assertNotNull(connector.iterate(new Filter()));
	}

}
