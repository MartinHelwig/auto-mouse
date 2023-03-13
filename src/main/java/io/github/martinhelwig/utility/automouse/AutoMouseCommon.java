// SPDX-FileCopyrightText: 2023 Martin Helwig
//
// SPDX-License-Identifier: MIT

package io.github.martinhelwig.utility.automouse;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.TrayIcon.MessageType;
import java.awt.desktop.UserSessionEvent;
import java.awt.desktop.UserSessionListener;
import java.io.IOException;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.github.martinhelwig.utility.automouse.common.ExcludeFromCodeCoverage;

/**
 * AutoMouseCommon is the common implementation of the {@link AutoMouseInterface}.
 * 
 * @author Martin Helwig
 *
 */
public class AutoMouseCommon implements AutoMouseInterface {

	private static Log logger = LogFactory.getLog(AutoMouseCommon.class);
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("io.github.martinhelwig.utility.automouse.languages.ApplicationResources");
	private Point lastLocation = null;
	private AutoMouseConfiguration configuration = null;
	protected boolean isScreenLocked = false;


	/**
	 * Default constructor.
	 * 
	 * @since 1.0.0
	 */
	public AutoMouseCommon() {
		this.lastLocation = MouseInfo.getPointerInfo().getLocation();
		this.configuration = new AutoMouseConfiguration();
		this.addEventListener();
	}


	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public Point moveMouse() throws AWTException {
		Point actualLocation = MouseInfo.getPointerInfo().getLocation();
		logger.debug("actual location: x=" + actualLocation.x + ", y=" + actualLocation.y);
		logger.debug("last location: x=" + lastLocation.x + ", y=" + lastLocation.y);
		if (lastLocation.x == actualLocation.x && lastLocation.y == actualLocation.y) {
			this.moveMouse(new Point(1, 1));
			this.moveMouse(new Point(lastLocation.x, lastLocation.y));
		}
		lastLocation = actualLocation;
		return actualLocation;
	}


	/**
     * {@inheritDoc}
     * 
     */
	@Override
	public Point moveMouse(Point point) throws AWTException {
		Robot robot = new Robot();
		robot.mouseMove(point.x, point.y);
		logger.debug("mouse moved to x=" + point.x + ", y=" + point.y);
		return point;
	}


	/**
     * {@inheritDoc}
     * 
     */
	@Override
	public void pushMessage(String message, MessageType messageType) {
		switch (messageType) {
		case ERROR:
			logger.error(message);
			break;
		case WARNING:
			logger.warn(message);
			break;
		case INFO:
			logger.info(message);
			break;
		case NONE:
			logger.info(message);
			break;
		}
	}


	/**
     * {@inheritDoc}
     * 
     */
	@Override
	public Point getLastLocation() {
		return this.lastLocation;
	}


	/**
     * {@inheritDoc}
     * 
     */
	@Override
	public void run() {
		boolean run = true;
		while (run) {
			try {
				if (!isScreenLocked) {
					this.moveMouse();
				}
				Thread.sleep(configuration.getTimer() * 1000);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				Thread.currentThread().interrupt();
			}
		}
		pushMessage(resourceBundle.getString("messages.exit"), MessageType.ERROR);
	}


	/**
     * {@inheritDoc}
     * 
     */
	@Override
	public AutoMouseConfiguration getConfiguration() {
		return this.configuration;
	}


	/**
     * {@inheritDoc}
     * 
     */
	@Override
	public void setCofiguration(AutoMouseConfiguration configuration) throws IOException {
		this.configuration = configuration;
		configuration.saveConfiguration();
	}


	/**
     * {@inheritDoc}
     * 
     */
	@Override
	public boolean isScreenLocked() {
		return this.isScreenLocked;
	}


	/**
	 * Adds an AppEventListener to verify if the screen is locked or not.
	 */
	@ExcludeFromCodeCoverage
	private void addEventListener() {
		Desktop desktop = Desktop.getDesktop();
		desktop.addAppEventListener(new UserSessionListener() {
			@Override
			@ExcludeFromCodeCoverage
			public void userSessionDeactivated(UserSessionEvent e) {
				isScreenLocked = true;
			}

			@Override
			@ExcludeFromCodeCoverage
			public void userSessionActivated(UserSessionEvent e) {
				isScreenLocked = false;
			}
		});
	}
}
