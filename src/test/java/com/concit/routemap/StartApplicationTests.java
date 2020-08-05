package com.concit.routemap;

import com.concit.routemap.service.RouteMapService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class StartApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	public RouteMapService service;

	@Test
	void citconTest1() {

		assertEquals("Yes",service.routeExists("Boston", "Newark"));
		assertEquals("Yes",service.routeExists("Boston", "Philadelphia"));
		assertEquals("No",service.routeExists("Philadelphia", "Albany"));


	}

}
