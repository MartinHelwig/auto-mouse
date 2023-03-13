// SPDX-FileCopyrightText: 2023 Martin Helwig
//
// SPDX-License-Identifier: MIT

package io.github.martinhelwig.utility.automouse;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.AWTException;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
public class AutoMouseConmmonTest {

	private AutoMouseInterface autoMouse = null;
	
	@BeforeEach
	public void setUp() {
		autoMouse = new AutoMouseCommon();
	}

	@Test
	@Order(1)
	protected void testNotNull() {
		try {
			assertNotNull(autoMouse.moveMouse());
			assertNotNull(autoMouse.getConfiguration());
			assertNotNull(autoMouse.getLastLocation());
			assertNotNull(autoMouse.isScreenLocked());
			autoMouse.setCofiguration(new AutoMouseConfiguration());
		} catch (AWTException | IOException e) {
			fail(e);
		}
	}
	
	@Test
	@Order(2)
	protected void testPushMessage() {
		try {
			autoMouse.pushMessage("JUnit execution - ERROR", MessageType.ERROR);
			autoMouse.pushMessage("JUnit execution - WARNING", MessageType.WARNING);
			autoMouse.pushMessage("JUnit execution - INFO", MessageType.INFO);
			autoMouse.pushMessage("JUnit execution - NONE", MessageType.NONE);
		} catch (Exception e) {
			fail(e);
		}
	}
	
	@Test
	@Order(3)
	protected void testRunnable() {
		try {
			autoMouse.getConfiguration().setTimer(1);
			ExecutorService service = Executors.newSingleThreadExecutor();
			service.execute(autoMouse);
			service.shutdown();
	    	service.awaitTermination(3, TimeUnit.SECONDS);
		} catch (Exception e) {
			fail(e);
		}
	}
	
	
	
	
}