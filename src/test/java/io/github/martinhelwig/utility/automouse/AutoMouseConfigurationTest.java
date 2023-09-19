// SPDX-FileCopyrightText: 2023 Martin Helwig
//
// SPDX-License-Identifier: MIT

package io.github.martinhelwig.utility.automouse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.github.martinhelwig.utility.automouse.common.Constants;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
public class AutoMouseConfigurationTest {

    /**
     * The configuration.
     */
    private AutoMouseConfiguration configuration = null;

    /**
     * Setup.
     */
    @BeforeEach
    public void setUp() {
        configuration = new AutoMouseConfiguration();
        try {
            Files.delete(
                    Paths.get(AutoMouseConfiguration.DEFAULT_CONFIGURATIONFILE
                            + "_TEST"));
        } catch (IOException e) {
        }
    }

    /**
     * Test 1.
     */
    @Test @Order(Constants.ONE)
    protected void testDefaultValues() {
        File configFile = new File(
                AutoMouseConfiguration.DEFAULT_CONFIGURATIONFILE + "_TEST");
        assumeFalse(configFile.exists());
        assertEquals(configuration.getTimer(),
                AutoMouseConfiguration.DEFAULT_TIMER);
    }

    /**
     * Test 2.
     */
    @Test @Order(Constants.TWO)
    protected void testTimer() {
        configuration.setConfigurationFile(
                AutoMouseConfiguration.DEFAULT_CONFIGURATIONFILE + "_TEST");
        configuration.setTimer(BigDecimal.TEN.intValue());
        assertEquals(configuration.getTimer(), BigDecimal.TEN.intValue());
    }

    /**
     * Test 3.
     */
    @Test @Order(Constants.THREE)
    protected void testSaveConfiguration() {
        configuration.setConfigurationFile(
                AutoMouseConfiguration.DEFAULT_CONFIGURATIONFILE + "_TEST");
        try {
            configuration.saveConfiguration();
        } catch (IOException e) {
            fail(e);
        }
    }

    /**
     * Test 4.
     */
    @Test @Order(Constants.FOUR)
    protected void testReloadConfiguration() {
        configuration.setConfigurationFile(
                AutoMouseConfiguration.DEFAULT_CONFIGURATIONFILE + "_TEST");
        try {
            configuration.saveConfiguration();
            configuration.reloadConfiguration();
        } catch (IOException | NullPointerException e) {
            fail(e);
        }
    }
}
