// SPDX-FileCopyrightText: 2023 Martin Helwig
//
// SPDX-License-Identifier: MIT

package io.github.martinhelwig.utility.automouse.manual;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.github.martinhelwig.utility.automouse.AutoMouseConfiguration;
import io.github.martinhelwig.utility.automouse.AutoMouseConfigurationPanel;

public final class TestConfigurationPanel {

    /**
     * The Logger.
     */
    private static Log logger = LogFactory.getLog(TestConfigurationPanel.class);

    /**
     * The default constructor.
     */
    private TestConfigurationPanel() {
    }

    /**
     * @param args actually no args required
     */
    public static void main(final String[] args) {
        // set look and feel to the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        SwingUtilities.invokeLater(() -> {
            new AutoMouseConfigurationPanel(new AutoMouseConfiguration())
                    .setVisible(true);
        });
    }
}
