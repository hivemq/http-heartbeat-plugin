/*
 * Copyright 2016 dc-square GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.hivemq.plugin.heartbeat.configuration;

import com.hivemq.spi.config.SystemInformation;
import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * The configuration reader which reads the "elasticbeam.properties" file.
 *
 * @author Dominik Obermaier
 */
public class ConfigurationReader {

    private static final Logger log = LoggerFactory.getLogger(ConfigurationReader.class);

    private static final String ELASTICBEAM_CONFIG = "heartbeat.properties";


    private final SystemInformation systemInformation;

    @Inject
    ConfigurationReader(final SystemInformation systemInformation) {
        this.systemInformation = systemInformation;
    }


    /**
     * Reads the elasticbeam.properties file from the HiveMQ config folder and initializes the {@link HeartBeatConfiguration}
     * object.
     * <p/>
     * If the config file could not be found, an error will occur that prohibits HiveMQ from starting up.
     *
     * @return the initialized configuration
     */
    public HeartBeatConfiguration readConfiguration() {

        final File file = new File(systemInformation.getConfigFolder(), ELASTICBEAM_CONFIG);
        try (FileInputStream fis = new FileInputStream(file)) {

            final Properties properties = new Properties();
            properties.load(fis);

            return ConfigFactory.create(HeartBeatConfiguration.class, properties);

        } catch (FileNotFoundException e) {
            log.error("Could not find {}. Does it exist?", file.getAbsolutePath(), e);
        } catch (IOException e) {
            log.error("Could not read {}. Is the file accessible?", file.getAbsolutePath(), e);
        } catch (Exception e) {
            log.error("An exception occurred which prevents HiveMQ from starting: ", e);
        }
        log.warn("Using default ElasticBeam Configuration");
        return ConfigFactory.create(HeartBeatConfiguration.class);
    }
}