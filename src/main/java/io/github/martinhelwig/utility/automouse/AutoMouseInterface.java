// SPDX-FileCopyrightText: 2023 Martin Helwig
//
// SPDX-License-Identifier: MIT

package io.github.martinhelwig.utility.automouse;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;

/**
 * AutoMouseInterface is an interface for the program.
 *
 * @author Martin Helwig
 * @version 1.0.0
 */
public interface AutoMouseInterface extends Runnable {

    /**
     * Moves the mouse if they is not at the same position as last move.
     *
     * @return the {@link Point} of the actual mouse location.
     * @see Point
     * @since 1.0.0
     * @throws AWTException if there was an error while moving the mouse.
     */
    Point moveMouse() throws AWTException;

    /**
     * Moves the mouse to the specific point.
     *
     * @param point the point where the move should move to.
     * @return the {@link Point} of the actual mouse location.
     * @since 1.0.0
     * @throws AWTException if there was an error while moving the mouse.
     */
    Point moveMouse(Point point) throws AWTException;

    /**
     * Pushes a message.
     *
     * @param message the message that should be pushed
     * @param messageType defines the message priority
     * @since 1.0.0
     */
    void pushMessage(String message, MessageType messageType);

    /**
     * Gets the last location of the mouse.
     *
     * @return the {@link Point} of the last mouse location.
     * @since 1.0.0
     */
    Point getLastLocation();

    /**
     * Gets the actual used {@link AutoMouseConfiguration}.
     *
     * @return the actual used configuration
     * @since 1.0.0
     */
    AutoMouseConfiguration getConfiguration();

    /**
     * Sets the {@link AutoMouseConfiguration} which should be used.
     *
     * @param configuration the configuration which should be used
     * @since 1.0.0
     * @throws IOException if the configuration cannot be saved.
     */
    void setCofiguration(AutoMouseConfiguration configuration)
            throws IOException;

    /**
     * Checks if the screen is actually locked.
     *
     * @since 1.0.0
     * @return true if the screen is locked, false if the screen is not locked.
     */
    boolean isScreenLocked();
}
