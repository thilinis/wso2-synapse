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

package org.apache.synapse.versioning.dispatch;

import org.apache.synapse.MessageContext;
import org.apache.synapse.SynapseConstants;
import org.apache.synapse.versioning.Version;
import org.apache.synapse.versioning.VersionConstants;

public class VersionedMainSequenceDispatcher extends AbstractDispatchStrategy {

    @Override
    public void specificDispatchRoutine(Object contextInfo, Target target) {
        if (contextInfo != null) {
            MessageContext msgCtxt = (MessageContext) contextInfo;
            msgCtxt.setProperty(VersionConstants.DISPATCH_VERSION, target.getTargetVersion());
            msgCtxt.setProperty(VersionConstants.DISPATCH_CONTEXT, target.getTarget());
        }

    }

    public Target executeDispatch(Object contextInfo, String t) {
        Target target = new Target();
        String epr = t.trim();
        String context = "";
        if (epr != null && !"".equals(epr)) {
            if (epr.matches(Version.PATTERN1_MAIN_EPR_VALIDATION)) {
                String versionStr = epr.split("[\\w\\-()!#_/]*[/]")[1];
                context = epr.split("[\\w\\-()!#_/]*[/]")[0];
                target.setVersion(versionStr + ".0.0");
            } else if (epr.matches(Version.PATTERN2_MAIN_EPR_VALIDATION)) {
                String versionStr = epr.split("[\\w\\-()!#_/]*[/]")[1];
                context = epr.split("[\\w\\-()!#_/]*[/]")[0];
                target.setVersion(versionStr + ".0");
            } else if (epr.matches(Version.PATTERN3_MAIN_EPR_VALIDATION)) {
                String versionStr = epr.split("[\\w\\-()!#_/]*[/]")[1];
                context = epr.split("[\\w\\-()!#_/]*[/]")[0];
                target.setVersion(versionStr);
            } else {
                target.setVersion(SynapseConstants.DEFAULT_ARTIFACT_VERSION);
                context = epr;
            }
        }
        if (contextInfo != null) {
            MessageContext msgCtxt = (MessageContext) contextInfo;
            msgCtxt.setProperty(VersionConstants.DISPATCH_VERSION, target.getTargetVersion());
            msgCtxt.setProperty(VersionConstants.DISPATCH_CONTEXT, context);
        }
        specificDispatchRoutine(contextInfo, target);
        return target;
    }

}
