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

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.DisableableFeature.PARAMETER_FORMATTING;
import static org.aeonbits.owner.Config.DisableableFeature.VARIABLE_EXPANSION;

@Config.DisableFeature({VARIABLE_EXPANSION, PARAMETER_FORMATTING})
public interface HeartBeatConfiguration extends Config {

    @Key("heartbeat.port")
    @DefaultValue("9090")
    int getHeartbeatPort();

    @Key("heartbeat.bindAddress")
    @DefaultValue("0.0.0.0")
    String getHeartbeatBindAddress();

    @Key("heartbeat.path")
    @DefaultValue("/heartbeat")
    String getHeartbeatPath();
}