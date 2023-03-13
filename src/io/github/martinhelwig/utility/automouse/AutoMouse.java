// SPDX-FileCopyrightText: 2023 Martin Helwig
//
// SPDX-License-Identifier: MIT

package io.github.martinhelwig.utility.automouse;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.github.martinhelwig.utility.automouse.common.ExcludeFromCodeCoverage;

/**
 * AutoMouse is the main class to start the program.
 * 
 * @author Martin Helwig
 *
 */
@ExcludeFromCodeCoverage
public class AutoMouse {

	private static Log logger = LogFactory.getLog(AutoMouse.class);
	
	/**
	 * Default constructor.
	 * 
	 * @since 1.0.0
	 */
	public AutoMouse() {
       logger.trace("AutoMouse() - start"); //$NON-NLS-1$
       logger.trace("AutoMouse() - end"); //$NON-NLS-1$
	}
	
	
	private void logLibraries() {
        logger.trace("logLibraries() - start"); //$NON-NLS-1$

		List<String> classpath = Arrays.asList(System.getProperty("java.class.path").split(File.pathSeparator));
        
        Iterator<String> classpathIterator = classpath.iterator();
        while (classpathIterator.hasNext()) {
        	String classpathEntry = classpathIterator.next(); 
            logger.info(classpathEntry);
        }

        logger.trace("logLibraries() - end"); //$NON-NLS-1$
	}


	private void runAutoMouse() throws AWTException {
	    logger.trace("runAutoMouse() - start"); //$NON-NLS-1$

		AutoMouseInterface autoMouse = null;
		if (SystemTray.isSupported() ) {
			autoMouse = new AutoMouseTrayIcon();
		} else {
			autoMouse = new AutoMouseCommon();
		}
		
		try {
			autoMouse.run();
		} catch (Exception e) {
			logger.error("an error occured, closing the program", e);
			System.exit(1);
		}

        logger.trace("runAutoMouse() - end"); //$NON-NLS-1$
	}


	public static void main(String[] args) {
		logger.info("AutoMouse started...");
		try {
			AutoMouse mainProgram = new AutoMouse();
			mainProgram.logLibraries();
			mainProgram.runAutoMouse();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
}
