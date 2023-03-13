// SPDX-FileCopyrightText: 2023 Martin Helwig
//
// SPDX-License-Identifier: MIT

package io.github.martinhelwig.utility.automouse;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
public class AutoMouseConfigurationPanelTest {

	private AutoMouseConfigurationPanel configurationPanel = null;
	
	@BeforeEach
	public void setUp() {
		
	}

	@Test
	@Order(1)
	protected void testDefaultValues() {
		configurationPanel = new AutoMouseConfigurationPanel(new AutoMouseConfiguration());
		configurationPanel.setVisible(false);
	}
}