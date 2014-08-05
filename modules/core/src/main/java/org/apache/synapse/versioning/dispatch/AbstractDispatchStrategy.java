
package org.apache.synapse.versioning.dispatch;

import org.apache.synapse.SynapseConstants;
import org.apache.synapse.versioning.Version;

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
public abstract class AbstractDispatchStrategy implements DispatcherStrategy {



    public abstract void specificDispatchRoutine(Object ctxt, Target t);

    public DispatcherStrategy.Target executeDispatch(Object contextInfo, String t) {
        DispatcherStrategy.Target target = new DispatcherStrategy.Target();
        String epr = t.trim();

        String context = "" ;
        if(epr != null && !"".equals(epr)){
            if(epr != null && !"".equals(epr)){
                if(epr.matches(Version.PATTERN1_KEY_VALIDATION)){
                    String versionStr = epr.split(Version.PATTERN1_VEREXP)[1];
                    target.setVersion(versionStr + ".0.0");
                    String targetStr = epr.split(Version.PATTERN1_TARGETEXP)[0];
                    target.setTarget(targetStr);
                }
                else if(epr.matches(Version.PATTERN2_KEY_VALIDATION)){
                    String versionStr = epr.split(Version.PATTERN2_VEREXP)[1];
                    target.setVersion(versionStr + ".0");
                    String targetStr = epr.split(Version.PATTERN2_TARGETEXP)[0];
                    target.setTarget(targetStr);
                }
                else if(epr.matches(Version.PATTERN3_KEY_VALIDATION)){
                    String versionStr = epr.split(Version.PATTERN3_VEREXP)[1];
                    target.setVersion(versionStr);
                    String targetStr = epr.split(Version.PATTERN3_TARGETEXP)[0];
                    target.setTarget(targetStr);
                }
                else if (epr.matches(Version.PATTERN_DEFAULT_KEY_VALIDATION)){
                    target.setVersion(SynapseConstants.DEFAULT_ARTIFACT_VERSION);
                    if (epr.endsWith("/")) {
                        target.setTarget(epr.substring(0, epr.length() - 1));
                    }else {
                        target.setTarget(epr);
                    }
                }
            }

        }
        specificDispatchRoutine(contextInfo, target);
        return target;
    }

}
