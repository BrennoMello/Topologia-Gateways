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
package com.ufba.ffd.utilities;

import com.ufba.ffd.entities.Device;
import com.ufba.ffd.entities.Gateway;
import com.ufba.ffd.entities.Topology;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Brenno Mello <brennodemello.bm at gmail.com>
 */
public class CoveringMatrix {
    
    private Map<Gateway, List<Device>> coveringMatrix;
    private List<Device> devicesUncovered;
    private Topology topology;
    
    public CoveringMatrix(Topology topology){
        this.topology = topology;
        coveringMatrix = new HashMap<>();
        devicesUncovered = new ArrayList<>();
        
        List<Device> listDevices = topology.getListDevices();
        List<Gateway> listGateways = topology.getListGateways();
        Map<Device, Boolean> devicesCovered = new HashMap<>();
        
        for(Device device : listDevices){
            devicesCovered.put(device, Boolean.FALSE);
        }
        
        for(Gateway gateway : listGateways){
            List<Device> devicesNear = new ArrayList<>();
            coveringMatrix.put(gateway, devicesNear);
            Coordinate gatewayCoordinate = gateway.getCoordinate();
            for(Device device : listDevices){
                Coordinate deviceCoordinate = device.getCoordinate();
                if(gatewayCoordinate.containsCoordinate(deviceCoordinate)){
                    devicesCovered.put(device, Boolean.TRUE);
                    devicesNear.add(device);
                }
            }
        }
        
        for(Device device : listDevices){
            if(devicesCovered.get(device).equals(Boolean.FALSE)){
                devicesUncovered.add(device);
            }
        }
    }

    public Map<Gateway, List<Device>> getCoveringMatrix() {
        return coveringMatrix;
    }

    public void setCoveringMatrix(Map<Gateway, List<Device>> coveringMatrix) {
        this.coveringMatrix = coveringMatrix;
    }

    public Topology getTopology() {
        return topology;
    }

    public void setTopology(Topology topology) {
        this.topology = topology;
    }

    public List<Device> getDevicesUncovered() {
        return devicesUncovered;
    }

    public void setDevicesUncovered(List<Device> devicesUncovered) {
        this.devicesUncovered = devicesUncovered;
    }
    
    @Override
    public String toString(){
        String ret = "CoveringMatrix => [\n";
        
        for(Gateway gateway : coveringMatrix.keySet()){
            ret = ret.concat("\t{\n\t\t" + gateway.getName() + " => [\n");
            for(Device device : coveringMatrix.get(gateway)){
                ret = ret.concat("\t\t\t" + device.getName() + ",\n");
            }
            ret = ret.concat("\t\t]\n\t},\n");
        }
        
        ret = ret.concat("\tUncovered => {\n");
        for(Device uncoveredDevice : devicesUncovered){
            ret = ret.concat("\t\t" + uncoveredDevice.getName() + ",\n");
        }
        ret = ret.concat("\t}\n");
        ret = ret.concat("]");
        
        return ret;
    }

}
