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
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Configuration Panel
 * 
 * @author Martin Helwig
 */
public class AutoMouseConfigurationPanel extends JFrame {

    private static final long serialVersionUID = -1335249051736338895L;
    private static Log logger = LogFactory.getLog(AutoMouseConfigurationPanel.class);
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("io.github.martinhelwig.utility.automouse.languages.ApplicationResources");
    private static JLabel labelTimer = new JLabel("Timer: ");
    private static JLabel message = new JLabel("* Enter only numeric digits(0-9)");
    private static JButton buttonSave = new JButton(resourceBundle.getString("button.save"));
    private static JTextField textTimer = new JTextField(20);

    public AutoMouseConfigurationPanel(AutoMouseConfiguration configuration) {
        super(resourceBundle.getString("programName"));
        logger.trace("AutoMouseConfigurationPanel(AutoMouseConfiguration=" + configuration.toString() + ") - start"); //$NON-NLS-1$ //$NON-NLS-2$
                
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/auto-mouse_black.png"));
        this.setIconImage(image);
        
        textTimer.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (logger.isDebugEnabled()) {
                    logger.debug("$KeyAdapter.keyPressed(KeyEvent) - start"); //$NON-NLS-1$
                }

            	textTimer.setEditable(false);
            	if ( (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || (ke.getKeyCode() == 8 || ke.getKeyCode() == 127 ) ) {
                    textTimer.setEditable(true);
                }

                if (logger.isDebugEnabled()) {
                    logger.debug("$KeyAdapter.keyPressed(KeyEvent) - end"); //$NON-NLS-1$
                }
            }
        });

        textTimer.setText(String.valueOf(configuration.getTimer()));
        logger.debug("set timer to " + configuration.getTimer());
        
        // create a new panel with GridBagLayout manager
        JPanel newPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

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
        newPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), resourceBundle.getString("title.configuration")));

        // add the panel to this frame
        add(newPanel);

        pack();
        setLocationRelativeTo(null);

        logger.trace("AutoMouseConfigurationPanel(AutoMouseConfiguration) - end"); //$NON-NLS-1$
    }
}