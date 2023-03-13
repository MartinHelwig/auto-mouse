// SPDX-FileCopyrightText: 2023 Martin Helwig
//
// SPDX-License-Identifier: MIT

package io.github.martinhelwig.utility.automouse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
public class AutoMouseConfigurationTest {

	private static final String TEST_CONFIGURATIONFILE = AutoMouseConfiguration.DEFAULT_CONFIGURATIONFILE + "_TEST";
	private AutoMouseConfiguration configuration = null;
	
	@BeforeEach
	public void setUp() {
		configuration = new AutoMouseConfiguration();
		 try {
			 Files.delete(Paths.get(TEST_CONFIGURATIONFILE));
		 } catch (IOException e) {
		 }
	}

	@Test
	@Order(1)
	protected void testDefaultValues() {
		File configFile = new File(AutoMouseConfiguration.DEFAULT_CONFIGURATIONFILE);
		assumeFalse(configFile.exists());
		assertEquals(configuration.getTimer(), AutoMouseConfiguration.DEFAULT_TIMER);
	}
	
	@Test
	@Order(2)
	protected void testTimer() {
		configuration.setConfigurationFile(TEST_CONFIGURATIONFILE);
		configuration.setTimer(10);
		assertEquals(configuration.getTimer(), 10);
	}
	
	@Test
	@Order(3)
	protected void testSaveConfiguration() {
		configuration.setConfigurationFile(TEST_CONFIGURATIONFILE);
		try {
			configuration.saveConfiguration();
		} catch (IOException e) {
			fail(e);
		}
	}
	
	@Test
	@Order(4)
	protected void testReloadConfiguration() {
		configuration.setConfigurationFile(TEST_CONFIGURATIONFILE);
		try {
			configuration.saveConfiguration();
			configuration.reloadConfiguration();
		} catch (IOException | NullPointerException e) {
			fail(e);
		}
	}
}