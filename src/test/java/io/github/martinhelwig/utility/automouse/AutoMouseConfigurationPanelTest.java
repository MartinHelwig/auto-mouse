// SPDX-FileCopyrightText: 2023 Martin Helwig
//
// SPDX-License-Identifier: MIT

package io.github.martinhelwig.utility.automouse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.github.martinhelwig.utility.automouse.common.Constants;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
public class AutoMouseConfigurationPanelTest {

    /**
     * The configuration panel.
     */
    private AutoMouseConfigurationPanel configurationPanel = null;

    /**
     * Setup.
     */
    @BeforeEach
    public void setUp() {

    }

    /**
     * Test 1.
     */
    @Test @Order(Constants.ONE)
    protected void testDefaultValues() {
        configurationPanel = new AutoMouseConfigurationPanel(
                new AutoMouseConfiguration());
        configurationPanel.setVisible(false);
    }
}
