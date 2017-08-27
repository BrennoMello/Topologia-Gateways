/*
 * Copyright 2017 brenno.
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author brenno
 */
public class Best {
    private Set<Gateway> selectedGateways;
    private Set<Device> accessibleDevices;
    private Map<Gateway, Set<Device>> mapDevices;
    
    public Best(){
        selectedGateways = new HashSet<>();
        accessibleDevices = new HashSet<>();
        mapDevices = new HashMap<>();
    }
    
    public Best(Set<Gateway> candidateGateways, Set<Device> candidateDevices, Map<Gateway, Set<Device>> candidateMapDevices){
        this.selectedGateways = candidateGateways;
        this.accessibleDevices = candidateDevices;
        this.mapDevices = candidateMapDevices;
    }
    
    public Best(Best oldBest){
        selectedGateways.addAll(oldBest.getSelectedGateways());
        accessibleDevices.addAll(oldBest.getAccessibleDevices());
        mapDevices.putAll(oldBest.getMapDevices());
    }
        
    public int getQtdGateways(){
        return selectedGateways.size();
    }

    public int getQtdDevices(){
        return accessibleDevices.size();
    }
    
    public Set<Gateway> getSelectedGateways(){
        return selectedGateways;
    }
    
    public Set<Device> getAccessibleDevices(){
        return accessibleDevices;
    }
    
    public Map<Gateway, Set<Device>> getMapDevices(){
        return mapDevices;
    }

    public void addGateway(Gateway gateway, Set<Device> devicesAssociated){
        selectedGateways.add(gateway);
        accessibleDevices.addAll(devicesAssociated);
        mapDevices.put(gateway, devicesAssociated);
    }
    
    public boolean tryRemoveOne(){
        for(Map.Entry<Gateway, Set<Device>> gateway : mapDevices.entrySet()){
            Set<Device> newDeviceAccessible = new HashSet<>();
            
            for(Map.Entry<Gateway, Set<Device>> newGateway : mapDevices.entrySet()){
                if(!newGateway.equals(gateway)){
                    newDeviceAccessible.addAll(newGateway.getValue());
                }
            }
            
            if(newDeviceAccessible.size() == accessibleDevices.size()){
                selectedGateways.remove(gateway.getKey());
                mapDevices.remove(gateway.getKey());
                return true;
            }
        }
        
        return false;
    }
}
