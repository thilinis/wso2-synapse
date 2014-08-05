
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
package org.apache.synapse.versioning;

import org.apache.synapse.Nameable;
import org.apache.synapse.SynapseConstants;


public class VersionConfiguration implements Nameable {

    private String name;
    private String version = SynapseConstants.DEFAULT_ARTIFACT_VERSION;
    private boolean isDefaultVersion;

    public VersionConfiguration(String name, String version) {
        setName(name);
        this.version = new Version(version).toString();
    }
    public VersionConfiguration(String name) {
        setName(name);
        this.version = new Version(SynapseConstants.DEFAULT_ARTIFACT_VERSION).toString();
    }

    public boolean isDefaultVersion() {
        return isDefaultVersion;
    }

    public void setDefaultVersion(boolean defaultVersion) {
        isDefaultVersion = defaultVersion;
    }

    public String getVersion() {
        return version;
    }

    public String getUUIDName() {
        return ArtifactVersionIdGenerator.getArtifactVersionKey(name, version);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
