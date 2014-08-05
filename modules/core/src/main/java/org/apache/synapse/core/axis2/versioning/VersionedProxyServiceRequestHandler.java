/*
*  Copyright (c) 2005-2010, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/
package org.apache.synapse.core.axis2.versioning;

import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.description.HandlerDescription;
import org.apache.axis2.dispatchers.AbstractServiceDispatcher;
import org.apache.axis2.engine.AxisConfiguration;
import org.apache.axis2.util.LoggingControl;
import org.apache.axis2.util.Utils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.versioning.ArtifactVersionIdGenerator;
import org.apache.synapse.versioning.dispatch.DispatcherStrategy;
import org.apache.synapse.versioning.dispatch.VersionedProxyServiceDispatcher;

public class VersionedProxyServiceRequestHandler extends AbstractServiceDispatcher {

    public static final String NAME = "VersionedProxyDispatcher";
    private VersionedProxyServiceDispatcher versionedProxyServiceDispatcher = new VersionedProxyServiceDispatcher();
    private static final Log log = LogFactory.getLog(VersionedProxyServiceRequestHandler.class);

    @Override
    public AxisService findService(MessageContext messageContext) throws AxisFault {
        EndpointReference toEPR = messageContext.getTo();
        if(toEPR != null){
            if (LoggingControl.debugLoggingAllowed && log.isDebugEnabled()) {
                log.debug(messageContext.getLogIDString() +
                        " Checking for Service using target endpoint address : " +
                        toEPR.getAddress());
            }
            String filePart = toEPR.getAddress();
            ConfigurationContext configurationContext = messageContext.getConfigurationContext();
            //Get the service/operation part from the request URL
            String serviceOpPart = Utils.getServiceAndOperationPart(filePart,
                    messageContext.getConfigurationContext().getServiceContextPath());

            if(serviceOpPart != null){
                DispatcherStrategy.Target target = versionedProxyServiceDispatcher.
                        executeDispatch(messageContext, serviceOpPart);
                //Generate dispatching service's UUID
                String serviceUUID = ArtifactVersionIdGenerator.
                        getArtifactVersionKey(target.getTarget(), target.getTargetVersion());

                AxisConfiguration registry = configurationContext.getAxisConfiguration();
                AxisService axisService = null;
                axisService = registry.getService(serviceUUID);
                if(axisService != null){
                    messageContext.setTo(new EndpointReference(
                            messageContext.getConfigurationContext().getServiceContextPath()+"/"+serviceUUID));
                    messageContext.setProperty(Constants.Configuration.TRANSPORT_IN_URL,
                            messageContext.getConfigurationContext().getServiceContextPath()+serviceUUID);
                }

                return axisService;
            }
            else {
                if (LoggingControl.debugLoggingAllowed && log.isDebugEnabled()) {
                    log.debug(messageContext.getLogIDString() +
                            " Attempted to check for Service using target endpoint URI, but the service fragment was missing");
                }
                return null;
            }

        }else{
            if (LoggingControl.debugLoggingAllowed && log.isDebugEnabled()) {
                log.debug(messageContext.getLogIDString() +
                        " Attempted to check for Service using null target endpoint URI");
            }
            return null;
        }
    }

    @Override
    public void initDispatcher() {
        init(new HandlerDescription(NAME));
    }
}
