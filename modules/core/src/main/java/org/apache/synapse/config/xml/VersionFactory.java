/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *   * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.apache.synapse.config.xml;

import org.apache.axiom.om.OMAttribute;
import org.apache.axiom.om.OMElement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.SynapseConstants;
import org.apache.synapse.SynapseException;
import org.apache.synapse.versioning.VersionConfiguration;

import javax.xml.namespace.QName;

public class VersionFactory {
    private static final Log log = LogFactory.getLog(VersionFactory.class);

    private static final QName ATT_VERSION  = new QName("version");

    public VersionConfiguration createVersionConfig(String artifactName, String attName, OMElement elem) {

        VersionConfiguration versionConfiguration = null;
        OMAttribute attVersion = null;
        if (elem != null) {
            attVersion = elem.getAttribute(new QName(attName));
        }

        if (attVersion != null) {
            String attributeValue = attVersion.getAttributeValue();
            versionConfiguration = new VersionConfiguration(artifactName, attributeValue);
            versionConfiguration.setDefaultVersion(false);
        } else {
            String attributeValue = SynapseConstants.DEFAULT_ARTIFACT_VERSION;
            versionConfiguration = new VersionConfiguration(artifactName, attributeValue);
            versionConfiguration.setDefaultVersion(true);
        }
        return versionConfiguration;
    }

    public VersionConfiguration createVersionConfig(String artifactName ,OMElement versionedElem) {
        return createVersionConfig(artifactName,ATT_VERSION.getLocalPart(),versionedElem);
    }
    private void handleException(String msg) {
        log.error(msg);
        throw new SynapseException(msg);
    }


}
