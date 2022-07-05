import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodName.class)
class ByName_Order {

	@Test
	void setup_test() {
		System.out.println("Setup Test");
	}
	
	@Test
	void login_test() {
		System.out.println("Login Test");
	}
	
	@Test
	void action_test() {
		System.out.println("Action Test");
	}
	
	@Test
	void logout_test() {
		System.out.println("Logout Test");
	}

}
