// SPDX-FileCopyrightText: 2023 Martin Helwig
//
// SPDX-License-Identifier: MIT

package io.github.martinhelwig.utility.automouse;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon.MessageType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
public class AutoMouseTrayIconTest {

    /**
     * The auto-mouse interface.
     */
    private AutoMouseInterface autoMouse = null;

    /**
     * Setup.
     */
    @BeforeEach
    public void setUp() {
        assumeTrue(SystemTray.isSupported());
        try {
            autoMouse = new AutoMouseTrayIcon();
        } catch (AWTException e) {
            fail(e);
        }
    }

    /**
     * Test 1.
     */
    @Test @Order(1)
    protected void testPushMessage() {
        assumeTrue(SystemTray.isSupported());
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
}
