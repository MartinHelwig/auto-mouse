// SPDX-FileCopyrightText: 2023 Martin Helwig
//
// SPDX-License-Identifier: MIT

package io.github.martinhelwig.utility.automouse;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.github.martinhelwig.utility.automouse.common.Constants;

/**
 * @author Martin Helwig
 *
 * @since 1.0.0
 */
public class AutoMouseTrayIcon extends AutoMouseCommon
        implements AutoMouseInterface {

    /**
     * The Logger.
     *
     * @since 1.0.0
     */
    private static Log logger = LogFactory.getLog(AutoMouseTrayIcon.class);

    /**
     * The resource bundle.
     *
     * @since 1.0.0
     */
    private static ResourceBundle resourceBundle = ResourceBundle
            .getBundle("io.github.martinhelwig.utility.automouse.languages."
                    + "ApplicationResources");

    /**
     * The program name.
     *
     * @since 1.0.0
     */
    private static String programName = resourceBundle.getString("programName");

    /**
     * The tray icon.
     *
     * @since 1.0.0
     */
    private TrayIcon trayIcon = null;

    /**
     * The default constructor.
     *
     * @since 1.0.0
     * @throws AWTException on error
     */
    public AutoMouseTrayIcon() throws AWTException {
        super();
        this.initSystemTray();
    }

    /**
     * This initialize the systemTray.
     *
     * @since 1.0.0
     * @throws AWTException
     */
    private void initSystemTray() throws AWTException {
        if (SystemTray.isSupported()) {
            logger.debug("system tray is supported");
        }
        SystemTray systemTray = SystemTray.getSystemTray();
        Image trayImage = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("images/auto-mouse_white.png"));
        Icon icon = new ImageIcon(
                new ImageIcon(trayImage).getImage().getScaledInstance(
                        Constants.FIVTY, Constants.FIVTY, Image.SCALE_DEFAULT));

        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem menuItemConfig = new JMenuItem(
                resourceBundle.getString("popupMenu.config"));
        menuItemConfig.addActionListener(actionListener -> {
            new AutoMouseConfigurationPanel(this.getConfiguration())
                    .setVisible(true);
        });
        popupMenu.add(menuItemConfig);

        JMenuItem menuItemInfo = new JMenuItem(
                resourceBundle.getString("popupMenu.info"));
        menuItemInfo.addActionListener(
                actionListener -> JOptionPane.showMessageDialog(null,
                        resourceBundle.getString("copyright"), programName,
                        JOptionPane.INFORMATION_MESSAGE, icon));
        popupMenu.add(menuItemInfo);

        JMenuItem menuItemExit = new JMenuItem(
                resourceBundle.getString("popupMenu.exit"));
        menuItemExit.addActionListener(actionListener -> System.exit(0));
        popupMenu.add(menuItemExit);

        trayIcon = new TrayIcon(trayImage, programName);
        trayIcon.setImageAutoSize(true);
        systemTray.add(trayIcon);

        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(final MouseEvent e) {
                maybeShowPopup(e);
            }

            @Override
            public void mousePressed(final MouseEvent e) {
                maybeShowPopup(e);
            }

            private void maybeShowPopup(final MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.setLocation(e.getX(), e.getY());
                    popupMenu.setInvoker(popupMenu);
                    popupMenu.setVisible(true);
                }
            }
        });

        trayIcon.setToolTip(
                programName + " - " + resourceBundle.getString("copyright"));
        trayIcon.displayMessage(programName,
                resourceBundle.getString("messages.start"), MessageType.INFO);
    }

    /**
     * Push the message.
     *
     * @since 1.0.0
     */
    @Override
    public void pushMessage(final String message,
            final MessageType messageType) {
        trayIcon.displayMessage(programName, message, messageType);
        super.pushMessage(message, messageType);
    }
}
