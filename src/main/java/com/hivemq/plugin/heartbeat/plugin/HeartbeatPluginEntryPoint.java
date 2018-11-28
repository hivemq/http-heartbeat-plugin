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

package com.hivemq.plugin.heartbeat.plugin;

import com.hivemq.plugin.heartbeat.callbacks.AddHTTPHeartbeatOnStartup;
import com.hivemq.spi.PluginEntryPoint;
import com.hivemq.spi.callback.registry.CallbackRegistry;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * @author Dominik Obermaier
 */
public class HeartbeatPluginEntryPoint extends PluginEntryPoint {


    private AddHTTPHeartbeatOnStartup addHTTPHeartbeatOnStartup;

    @Inject
    HeartbeatPluginEntryPoint(final AddHTTPHeartbeatOnStartup addHTTPHeartbeatOnStartup) {

        this.addHTTPHeartbeatOnStartup = addHTTPHeartbeatOnStartup;
    }

    @PostConstruct
    public void postConstruct() {

        final CallbackRegistry callbackRegistry = getCallbackRegistry();
        callbackRegistry.addCallback(addHTTPHeartbeatOnStartup);

    }

}
