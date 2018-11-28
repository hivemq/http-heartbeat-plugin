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

package com.hivemq.plugin.heartbeat.ioc;

import com.hivemq.plugin.heartbeat.configuration.ConfigurationReader;
import com.hivemq.plugin.heartbeat.configuration.HeartBeatConfiguration;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * The provider which is responsible for creating the HeartBeatConfiguration object
 *
 * @author Dominik Obermaier
 */
public class HeartbeatConfigurationProvider implements Provider<HeartBeatConfiguration> {

    private final ConfigurationReader configReader;

    @Inject
    HeartbeatConfigurationProvider(final ConfigurationReader configReader) {
        this.configReader = configReader;
    }

    @Override
    public HeartBeatConfiguration get() {
        //We're actually reading the config file
        return configReader.readConfiguration();
    }
}