/*
 * Copyright 2017 Brenno Mello <brennodemello.bm at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ufba.ffd.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brenno Mello <brennodemello.bm at gmail.com>
 */
public class Topology {
    
    private List<Gateway> listGateways;
    private List<Device> listDevices;
    
    public Topology(List<Gateway> listGateways, List<Device> listDevices){
        this.listGateways = listGateways;
        this.listDevices = listDevices;
    }
    
    public Topology(){
        this(new ArrayList<>(), new ArrayList<>());
    }

    public List<Gateway> getListGateways() {
        return listGateways;
    }

    public void setListGateways(List<Gateway> listGateways) {
        this.listGateways = listGateways;
    }

    public List<Device> getListDevices() {
        return listDevices;
    }

    public void setListDevices(List<Device> listDevices) {
        this.listDevices = listDevices;
    }
    
    @Override
    public String toString(){
        String ret = "Topology => {\n";
        
        for(Device dev : listDevices){
            ret = ret.concat("\t" + dev.toString() + ", \n");
        }
        
        ret = ret.concat("}");
        
        return ret;
    }
    
}
