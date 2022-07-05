import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class Default_Order {

	@Test
	@Order(2)
	void setup_test() {
		System.out.println("Setup Test");
	}
	
	@Test
	@Order(1)
	void login_test() {
		System.out.println("Login Test");
	}
	
	@Test
	@Order(3)
	void action_test() {
		System.out.println("Action Test");
	}
	
	@Test
	@Order(4)
	void logout_test() {
		System.out.println("Logout Test");
	}

}
