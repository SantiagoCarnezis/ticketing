package com.scarnezis.ticketing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//@AutoConfigureTestDatabase
//@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
class TicketingApplicationTests {

	@Test
	void contextLoads() {
	}

//	@Test
//	public void test1() {
//
//		System.out.println("Este test corre bien");
//		Assertions.assertTrue(true);
//	}

//	@Test
//	public void test2() {
//
//		System.out.println("Este test corre mal");
//		Assertions.assertTrue(false);
//	}
}
