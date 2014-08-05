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


public class VersionedProxyServiceDispatcher extends AbstractDispatchStrategy {

    @Override
    public void specificDispatchRoutine(Object ctxt, Target t) {
        //do nothing for now

    }

//    public Target executeDispatch(Object contextInfo, String t) {
//        Target target = new Target();
//        String epr = t.trim();
//
//        String context = "" ;
//        if(epr != null && !"".equals(epr)){
//                if(epr != null && !"".equals(epr)){
//                    if(epr.matches(PATTERN1_KEY_VALIDATION)){
//                        String versionStr = epr.split("[\\w][\\w\\-()!#_/]*[/]")[1];
//                        target.setVersion(versionStr + ".0.0");
//                        String targetStr = epr.split("[/][\\d]*[\\d]")[0];
//                        target.setTarget(targetStr);
//                    }
//                    else if(epr.matches(PATTERN2_KEY_VALIDATION)){
//                        String versionStr = epr.split("[\\w][\\w\\-()!#_/]*[/]")[1];
//                        target.setVersion(versionStr + ".0");
//                        String targetStr = epr.split("[/][\\d]+[.][\\d]+")[0];
//                        target.setTarget(targetStr);
//                    }
//                    else if(epr.matches(PATTERN3_KEY_VALIDATION)){
//                        String versionStr = epr.split("[\\w][\\w\\-()!#_/]*[/]")[1];
//                        target.setVersion(versionStr);
//                        String targetStr = epr.split("[/][\\d]+[.][\\d]+[.][\\w-]+")[0];
//                        target.setTarget(targetStr);
//                    }
//                    else if (epr.matches(PATTERN_DEFAULT_KEY_VALIDATION)){
//                        target.setVersion(SynapseConstants.DEFAULT_SEQUNCE_VERSION);
//                        target.setTarget(epr);
//                    }
//                }
//
//        }
//        return target;
//    }

}
