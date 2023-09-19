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

import io.github.martinhelwig.utility.automouse.common.Constants;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
public class AutoMouseConmmonTest {

    /**
     * The auto-mouse interface.
     */
    private AutoMouseInterface autoMouse = null;

    /**
     * Setup.
     */
    @BeforeEach
    public void setUp() {
        autoMouse = new AutoMouseCommon();
    }

    /**
     * Test 1.
     */
    @Test @Order(Constants.ONE)
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

    /**
     * Test 2.
     */
    @Test @Order(Constants.TWO)
    protected void testPushMessage() {
        try {
            autoMouse.pushMessage("JUnit execution - ERROR", MessageType.ERROR);
            autoMouse.pushMessage("JUnit execution - WARNING",
                    MessageType.WARNING);
            autoMouse.pushMessage("JUnit execution - INFO", MessageType.INFO);
            autoMouse.pushMessage("JUnit execution - NONE", MessageType.NONE);
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test 3.
     */
    @Test @Order(Constants.THREE)
    protected void testRunnable() {
        try {
            autoMouse.getConfiguration().setTimer(1);
            ExecutorService service = Executors.newSingleThreadExecutor();
            service.execute(autoMouse);
            service.shutdown();
            service.awaitTermination(Constants.THREE, TimeUnit.SECONDS);
        } catch (Exception e) {
            fail(e);
        }
    }
}
