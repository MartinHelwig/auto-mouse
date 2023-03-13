// SPDX-FileCopyrightText: 2023 Martin Helwig
//
// SPDX-License-Identifier: MIT

package io.github.martinhelwig.utility.automouse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * AutoMouseConfiguration provides the whole configuration of the AutoMouse program.
 * 
 * @author Martin Helwig
 * @version 1.0.0
 *
 */
public class AutoMouseConfiguration {

	private static Log logger = LogFactory.getLog(AutoMouseConfiguration.class);
	private static final String CONFIG_KEY_TIMER="timer";
	private String configurationFile = DEFAULT_CONFIGURATIONFILE;
	private Properties configurationProperties = new Properties();
	
	protected static final long DEFAULT_TIMER = 60;
	protected static final String DEFAULT_CONFIGURATIONFILE = System.getProperty("user.home") + "/.auto-mouse/auto-mouse.properties";
	
	
	/**
	 * The default constructor.
	 * Loads the default configuration from the file if it exists.
	 * 
	 * @since 1.0.0
	 */
	public AutoMouseConfiguration() {
	    logger.trace("AutoMouseConfiguration() - start"); //$NON-NLS-1$
		
	    try {
			this.reloadConfiguration();
		} catch (NullPointerException | IOException e) {
			logger.warn("using default configuration", e);
		}
		
	    logger.trace("AutoMouseConfiguration() - end"); //$NON-NLS-1$
	}
	
	
	/**
	 * Getting the configuration file.
     * 
     * @return the configurationFile
     * @see String
     * @since 1.0.0
     */
	public String getConfigurationFile() {
        logger.trace("getConfigurationFile() - start"); //$NON-NLS-1$
        logger.trace("getConfigurationFile() - end - return value=" + this.configurationFile); //$NON-NLS-1$
		return this.configurationFile;
	}
	

	/**
	 * Sets the configuration file to use.
	 * 
	 * @param configurationFile the configurationFile to set
	 * @since 1.0.0
	 */
	public void setConfigurationFile(String configurationFile) {
        logger.trace("setConfigurationFile(String configurationFile=" + configurationFile + ") - start"); //$NON-NLS-1$ //$NON-NLS-2$
        
		this.configurationFile = configurationFile;

        logger.trace("setConfigurationFile(String) - end"); //$NON-NLS-1$
	}


	/**
	 * Gets the timer value.
	 * 
	 * @return the timer
	 * @see Long
	 * @since 1.0.0
	 */
	public Long getTimer() {
        logger.trace("getTimer() - start"); //$NON-NLS-1$
        long timer = Long.parseLong(configurationProperties.getProperty(CONFIG_KEY_TIMER, Long.toString(DEFAULT_TIMER)));
        logger.trace("getTimer() - end"); //$NON-NLS-1$
		return timer;
	}


	/**
	 * Sets the timer value.
	 * 
	 * @param timer the timer to set
	 * @since 1.0.0
	 */
	public void setTimer(long timer) {
        logger.trace("setTimer(long) - start"); //$NON-NLS-1$
        
		configurationProperties.setProperty(CONFIG_KEY_TIMER, Long.toString(timer));

        logger.trace("setTimer(long) - end"); //$NON-NLS-1$
	}
	
	
	/**
	 * Reloads the configuration from file.
	 * 
	 * @throws IOException if configuration file cannot be read
	 * @since 1.0.0
	 */
	public void reloadConfiguration() throws IOException {
	    logger.trace("reloadConfiguration() - start"); //$NON-NLS-1$
        
    	try (InputStream inputStream = new FileInputStream(this.configurationFile);) {
    		configurationProperties.load(inputStream);
		}

        logger.trace("reloadConfiguration() - end"); //$NON-NLS-1$
	}


	/**
	 * Saves the configuration to file.
	 * 
	 * @throws IOException if configuration file cannot be written
	 * @since 1.0.0
	 */
	public void saveConfiguration() throws IOException {
        logger.trace("saveConfiguration() - start"); //$NON-NLS-1$

		File configFile = new File(configurationFile);
		File configDirectory = new File(configFile.getParent());
    	configDirectory.mkdirs();
    	
    	try (FileOutputStream fileOutputStream = new FileOutputStream(configFile);) {
    		configurationProperties.store(fileOutputStream, "AutoMouse");
    	}

        logger.trace("saveConfiguration() - end"); //$NON-NLS-1$
	}
}
