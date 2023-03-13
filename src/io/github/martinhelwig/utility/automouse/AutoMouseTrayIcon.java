// SPDX-FileCopyrightText: 2023 Martin Helwig
//
// SPDX-License-Identifier: MIT

package io.github.martinhelwig.utility.automouse;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.util.ResourceBundle;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author Martin Helwig
 *
 * @since 1.0.0
 */
public class AutoMouseTrayIcon extends AutoMouseCommon implements AutoMouseInterface {

	private static Log logger = LogFactory.getLog(AutoMouseTrayIcon.class);
	private static ResourceBundle resourceBundle = ResourceBundle
			.getBundle("io.github.martinhelwig.utility.automouse.languages.ApplicationResources");
	private static String programName = resourceBundle.getString("programName");
	private TrayIcon trayIcon = null;
	
	public AutoMouseTrayIcon() throws AWTException {
		super();
		this.initSystemTray();
	}

	/**
	 * initialize the systemTray
	 * 
	 * @throws AWTException
	 */
	private void initSystemTray() throws AWTException {
		if (SystemTray.isSupported()) {
			logger.debug("system tray is supported");
		}
		SystemTray systemTray = SystemTray.getSystemTray();
		Image trayImage = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/auto-mouse_white.png"));
		Icon icon = new ImageIcon(new ImageIcon(trayImage).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

		PopupMenu popupMenu = new PopupMenu();

		MenuItem menuItemConfig = new MenuItem(resourceBundle.getString("popupMenu.config"));
		menuItemConfig.addActionListener(actionListener -> {
			new AutoMouseConfigurationPanel(this.getConfiguration()).setVisible(true);
		});
		popupMenu.add(menuItemConfig);

		MenuItem menuItemInfo = new MenuItem(resourceBundle.getString("popupMenu.info"));
		menuItemInfo.addActionListener(actionListener -> JOptionPane.showMessageDialog(null,
				resourceBundle.getString("copyright"), programName,
				JOptionPane.INFORMATION_MESSAGE, icon));
		popupMenu.add(menuItemInfo);

		MenuItem menuItemExit = new MenuItem(resourceBundle.getString("popupMenu.exit"));
		menuItemExit.addActionListener(actionListener -> System.exit(0));
		popupMenu.add(menuItemExit);

		trayIcon = new TrayIcon(trayImage, programName, popupMenu);
		trayIcon.setImageAutoSize(true);
		systemTray.add(trayIcon);
		trayIcon.setToolTip(programName + " - " + resourceBundle.getString("copyright"));
		trayIcon.displayMessage(programName, resourceBundle.getString("messages.start"), MessageType.INFO);
	}
    
	@Override
	public void pushMessage(String message, MessageType messageType) {
		trayIcon.displayMessage(programName, message, messageType);
		super.pushMessage(message, messageType);
	}
}
