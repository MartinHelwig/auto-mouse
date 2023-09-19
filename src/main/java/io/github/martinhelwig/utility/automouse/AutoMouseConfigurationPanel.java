// SPDX-FileCopyrightText: 2023 Martin Helwig
//
// SPDX-License-Identifier: MIT

package io.github.martinhelwig.utility.automouse;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.github.martinhelwig.utility.automouse.common.Constants;

/**
 * The Configuration Panel.
 *
 * @author Martin Helwig
 */
public class AutoMouseConfigurationPanel extends JFrame {

    /**
     * Serial version uid.
     *
     * @since 1.0.0
     */
    private static final long serialVersionUID = -1335249051736338895L;

    /**
     * The logger.
     *
     * @since 1.0.0
     */
    private static Log logger = LogFactory
            .getLog(AutoMouseConfigurationPanel.class);

    /**
    * The reource bundle.
    *
    * @since 1.0.0
    */
    private static ResourceBundle resourceBundle = ResourceBundle
            .getBundle("io.github.martinhelwig.utility.automouse.languages."
                    + "ApplicationResources");

    /**
    * The label for timer.
    *
    * @since 1.0.0
    */
    private static JLabel labelTimer = new JLabel("Timer: ");

    /**
    * The message.
    *
    * @since 1.0.0
    */
    private static JLabel message = new JLabel(
            "* Enter only numeric digits(0-9)");

    /**
    * The save button.
    *
    * @since 1.0.0
    */
    private static JButton buttonSave = new JButton(
            resourceBundle.getString("button.save"));

    /**
    * The text timer.
    *
    * @since 1.0.0
    */
    private static JTextField textTimer = new JTextField(Constants.TWENTY);

    /**
     * The default constructor.
     *
     * @since 1.0.0
     * @param configuration the configuration to use
     */
    public AutoMouseConfigurationPanel(
            final AutoMouseConfiguration configuration) {
        super(resourceBundle.getString("programName"));
        logger.trace("AutoMouseConfigurationPanel(AutoMouseConfiguration="
                + configuration.toString() + ") - start"); //$NON-NLS-1$

        Image image = Toolkit.getDefaultToolkit().getImage(
                getClass().getResource("images/auto-mouse_black.png"));
        this.setIconImage(image);

        textTimer.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent ke) {
                if (logger.isDebugEnabled()) {
                    logger.debug("KeyAdapter." + "keyPressed(KeyEvent) - "
                            + "start"); // $NON-NLS-2$
                }

                textTimer.setEditable(false);
                if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9')
                        || (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE
                                || ke.getKeyCode() == KeyEvent.VK_DELETE)) {
                    textTimer.setEditable(true);
                }

                if (logger.isDebugEnabled()) {
                    logger.debug("KeyAdapter." + "keyPressed(KeyEvent) - end");
                }
            }
        });

        textTimer.setText(String.valueOf(configuration.getTimer()));
        logger.debug("set timer to " + configuration.getTimer());

        // create a new panel with GridBagLayout manager
        JPanel newPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(BigDecimal.TEN.intValue(),
                BigDecimal.TEN.intValue(), BigDecimal.TEN.intValue(),
                BigDecimal.TEN.intValue());

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(labelTimer, constraints);

        constraints.gridx = 1;
        newPanel.add(textTimer, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        newPanel.add(message, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonSave, constraints);
        buttonSave.addActionListener(a -> {
            try {
                configuration.setTimer(Long.valueOf(textTimer.getText()));
                configuration.saveConfiguration();
            } catch (NumberFormatException | IOException e) {
                logger.error(e);
            }
        });

        // set border for the panel
        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                resourceBundle.getString("title.configuration")));

        // add the panel to this frame
        add(newPanel);

        pack();
        setLocationRelativeTo(null);

        logger.trace("AutoMouseConfigurationPanel(AutoMouseConfiguration) - "
                + "end"); //$NON-NLS-1$
    }
}
