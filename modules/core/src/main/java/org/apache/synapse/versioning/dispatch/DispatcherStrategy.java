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

public interface DispatcherStrategy {

    /**
     * This method dispatch to a respective versioned synapse artifact based on contextual inforamtion
     * (ie:- message context) and the input String (ie:- key , uri , message context ,etc)
     *
     * @param contextInfo
     * @param input
     * @return Target dispatch is success or not
     */
    public Target executeDispatch(Object contextInfo, String input);


    public static class Target {

        private String target = "";
        private String version = "";

         /**
         * get the Target artifact to dispatch to
         *
         * @return
         */
        public String getTarget(){
            return target;
        }

        /**
         * get the Version of the artifact to dispatch to
         * @return
         */
        public String getTargetVersion(){
            return version;
        }

        public boolean isDispatchSuccess(){
            return version != null && target != null ;
        }

        protected void setTarget(String target) {
            this.target = target;
        }

        protected void setVersion(String version) {
            this.version = version;
        }
    }

}
