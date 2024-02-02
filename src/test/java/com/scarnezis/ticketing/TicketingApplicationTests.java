package com.scarnezis.ticketing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TicketingApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void test1() {

		System.out.println("Este test corre bien");
		Assertions.assertTrue(true);
	}

	@Test
	public void test2() {

		System.out.println("Este test corre mal");
		Assertions.assertTrue(false);
	}
}
