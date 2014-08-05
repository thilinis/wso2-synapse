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

import org.apache.synapse.SynapseException;

/**
 * This class represents a Versioned artifact.
 * Version has follwing expression [0-9]+[.]?[0-9]*[.][0-9|a-z|A-Z]+
 * Followoing examples are valid versions for Synapse Artifacts
 * ie:- 0.0.0 , 1.0.0 , 2.1.M-1 , 10.0.foo , 1.0 , 2
 * <p/>
 * Following examples are not valid
 * ie:- 0.0 , 0  --> 0.0.0 is unique default sequence
 * 2. , 1.1. , .2 ,
 * <p/>
 * A version would be transformed to following canonical form in general
 * [0-9]+[.][0-9]+[.][0-9|a-z|A-Z]+
 */
public class Version {

    public static String vPattern1 = "[\\d]*";
    public static String vPattern2 = "[\\d]+[.][\\d]+";
    public static String vPattern3 = "[\\d]+[.][\\d]+[.][\\w-]+";
    private String version;

    /**
     * constants used for dispatching different versioned artifacts
     */
    public static final String PATTERN1_KEY_VALIDATION = "[\\w][\\w\\-()!#_/]*[/][\\d]*[\\d]" ;
    public static final String PATTERN2_KEY_VALIDATION ="[\\w][\\w\\-()!#_/]*[/][\\d]+[.][\\d]+" ;
    public static final String PATTERN3_KEY_VALIDATION = "[\\w][\\w\\-()!#_/]*[/][\\d]+[.][\\d]+[.][\\w-]+" ;
    public static final String PATTERN_DEFAULT_KEY_VALIDATION = "[\\w][\\w\\-()!#_/]*" ;

    public static final String PATTERN1_MAIN_EPR_VALIDATION = "[\\w\\-()!#_/]*[/][\\d]*[\\d]";
    public static final String PATTERN2_MAIN_EPR_VALIDATION = "[\\w\\-()!#_/]*[/][\\d]+[.][\\d]+";
    public static final String PATTERN3_MAIN_EPR_VALIDATION = "[\\w\\-()!#_/]*[/][\\d]+[.][\\d]+[.][\\w-]+";

    public static final String PATTERN1_VEREXP = "[\\w][\\w\\-()!#_/]*[/]";
    public static final String PATTERN1_TARGETEXP = "[/][\\d]*[\\d]";
    public static final String PATTERN2_VEREXP = "[\\w][\\w\\-()!#_/]*[/]";
    public static final String PATTERN2_TARGETEXP = "[/][\\d]+[.][\\d]+";
    public static final String PATTERN3_VEREXP = "[\\w][\\w\\-()!#_/]*[/]";
    public static final String PATTERN3_TARGETEXP = "[/][\\d]+[.][\\d]+[.][\\w-]+";



    public Version(String versionStr) {
        if (versionStr != null && versionStr.matches(vPattern1)) {
            version = versionStr + ".0.0" ;
        } else if (versionStr != null && versionStr.matches(vPattern2)) {
            version = versionStr + ".0" ;
        } else if (versionStr != null && versionStr.matches(vPattern3)) {
            version = versionStr ;
        } else {
            throw new SynapseException(new NumberFormatException("Version Number Format is Invalid!  : " + versionStr));
        }
    }

    public String toString(){
        return version ;
    }

}
