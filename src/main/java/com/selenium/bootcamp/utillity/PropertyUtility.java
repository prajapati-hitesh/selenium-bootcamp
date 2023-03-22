package com.selenium.bootcamp.utillity;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PropertyUtility {
    private static final Logger logger = LogManager.getLogger(PropertyUtility.class.getName());
    private final Properties prop;
    private FileInputStream fileInputStream;
    private String SPLIT_BY;
    private String propertyFilePath;
    private Parameters parameters;
    private FileBasedConfigurationBuilder<FileBasedConfiguration> propertyConfigurationBuilder;
    private Configuration configuration;

    public PropertyUtility() {
        this.prop = new Properties();
    }

    /**
     * Set Split By to use for properties value for separation
     *
     * @param splitBy Character to Split By
     */
    public void setSplitBy(String splitBy) {
        this.SPLIT_BY = splitBy;
    }

    /**
     * Set Property File Path
     *
     * @param propFilePath Properties File Path
     */
    public void setPropertyFilePath(String propFilePath) {
        this.propertyFilePath = propFilePath;
    }

    /**
     * This method will load the existing property file for modification
     * and initializes the property configuration object for writing.
     */
    public void loadPropertiesConfiguration() {
        try {
            this.parameters = new Parameters();

            this.propertyConfigurationBuilder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                    .configure(parameters.properties().setFileName(this.propertyFilePath));

            this.configuration = propertyConfigurationBuilder.getConfiguration();


        } catch (ConfigurationException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * This method will load property file for Reading Purpose.
     */
    public void load() {
        try {
            this.fileInputStream = new FileInputStream(this.propertyFilePath);

            // Initialize properties file
            prop.load(fileInputStream);

        } catch (IOException ex) {
            logger.error("Error Loading Properties File located at : " + propertyFilePath);
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * This method will update the existing property value
     *
     * @param propKey   Existing property Key
     * @param propValue New value of the mentioned key
     */
    public void updateProperty(String propKey, String propValue) {
        this.configuration.setProperty(propKey, propValue);
    }

    /**
     * This method will add new Property Key & Value to the
     * property file.
     *
     * @param propKey   Property Key to add
     * @param propValue Property value to add
     */
    public void addProperty(String propKey, String propValue) {
        this.configuration.addProperty(propKey, propValue);
    }

    /**
     * This method will save the updated/added key values to the property file.
     *
     * @throws ConfigurationException
     */
    public void saveProperty() throws ConfigurationException {
        this.propertyConfigurationBuilder.save();
    }


    /**
     * This method will get the locator type from the Key passed as
     * parameter to this method.
     *
     * @param propertyKey Property key
     * @return Locator type at index {0}
     */
    public String getLocatorType(String propertyKey) {
        String propValue = this.getProperty(propertyKey);
        List<String> listOfValue = Arrays.asList(propValue.trim().split(SPLIT_BY));
        return listOfValue.get(0);
    }

    /**
     * This method will get the locator value from the key passed as
     * Parameter to this method
     *
     * @param propertyKey Property Key
     * @return Locator Value at index {1}
     */
    public String getLocatorValue(String propertyKey) {
        String propValue = this.getProperty(propertyKey);
        List<String> listOfValue = Arrays.asList(propValue.trim().split(SPLIT_BY));
        return listOfValue.get(1);
    }

    /**
     * This will return the value for specified property key.
     *
     * @param propertyKey Property Key for which the Value to Get
     * @return Property Value
     */
    public String getProperty(String propertyKey) {
        return this.prop.getProperty(propertyKey).trim();
    }
}
