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

package com.hivemq.plugin.heartbeat.servlet;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The servlet that responds to the heartbeat GET request from the Load Balancer
 *
 * @author Dominik Obermaier
 */
public class HTTPHeartbeatServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(HTTPHeartbeatServlet.class);

    private MetricRegistry metricRegistry;

    @Inject
    public HTTPHeartbeatServlet(final MetricRegistry metricRegistry) {
        this.metricRegistry = metricRegistry;
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

        log.debug("Heartbeat Request from IP {} (port {}) received on listener {}:{} and URI {}",
                req.getRemoteAddr(), req.getRemotePort(), req.getLocalAddr(), req.getLocalPort(), req.getRequestURI());

        //We're just returning a 200
        resp.setStatus(HttpServletResponse.SC_OK);

        //We're adding additional metrics
        final Meter meter = metricRegistry.meter("http-heartbeat-meter");
        meter.mark();

    }
}
