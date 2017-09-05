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
import java.util.Random;
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
    
    public void tryRemoveOne(){
        Set<Gateway> gatewaysToRemove = new HashSet<>();
        for(Map.Entry<Gateway, Set<Device>> gateway : mapDevices.entrySet()){
            Set<Device> newDeviceAccessible = new HashSet<>();
            
            for(Map.Entry<Gateway, Set<Device>> newGateway : mapDevices.entrySet()){
                if(!newGateway.equals(gateway) && !gatewaysToRemove.contains(gateway.getKey())){
                    // COMENTA A LINHA ABAIXO PARA PERCEBER COMO TUDO MELHORA!!
                    newDeviceAccessible.addAll(newGateway.getValue());
                }
            }
            
            if(newDeviceAccessible.size() == accessibleDevices.size()){
                selectedGateways.remove(gateway.getKey());
                gatewaysToRemove.add(gateway.getKey());
                System.out.println(" >> Removendo gateway " + gateway.toString());
            }
        }
        
        for(Gateway gateway : gatewaysToRemove){
            mapDevices.remove(gateway);
        }
    }
    
    public void trySwap2per1(Map<Gateway, Set<Device>> outside_gateways){
        Random random_iter = new Random();
                
        Set<Integer> gatewaysExcludedInside = new HashSet<>();
        Set<Integer> gatewaysExcludedOutside = new HashSet<>();
        boolean goodSelection;
        
        Gateway[] arraySelectedGateways = selectedGateways.toArray(new Gateway[selectedGateways.size()]);
        Map.Entry<Gateway, Set<Device>>[] arrayOutsideGateways = outside_gateways.entrySet().toArray(new Map.Entry[outside_gateways.entrySet().size()]);
        
        int i = 0;
        int MAX_ITER = 10;
        
        do{

            Integer in1;
            Integer in2;
            Integer out1;
            
            if((gatewaysExcludedOutside.size() == outside_gateways.size()) || ((gatewaysExcludedInside.size() - arraySelectedGateways.length) < 2)){
                return;
            }
            
            while(gatewaysExcludedInside.contains(in1 = random_iter.nextInt(selectedGateways.size()))){}
            while((in2 = random_iter.nextInt(selectedGateways.size())).equals(in1) || gatewaysExcludedInside.contains(in2)){}
            while(gatewaysExcludedOutside.contains(out1 = random_iter.nextInt(outside_gateways.size()))){}            
            
            Gateway gatewayRemove1 = arraySelectedGateways[in1];
            Gateway gatewayRemove2 = arraySelectedGateways[in2];
            Gateway gatewayInsert = arrayOutsideGateways[out1].getKey();            
            Set<Device> devicesInsert = arrayOutsideGateways[out1].getValue();
            
            gatewaysExcludedInside.add(in1);
            gatewaysExcludedInside.add(in2);
            gatewaysExcludedOutside.add(out1);
            
            Map<Gateway, Set<Device>> newGateways = new HashMap<>(mapDevices);
            newGateways.remove(gatewayRemove1);
            newGateways.remove(gatewayRemove2);
            newGateways.put(gatewayInsert, devicesInsert);
            Set<Device> newDevices = new HashSet<>();
            
            for(Map.Entry<Gateway, Set<Device>> newGateway : newGateways.entrySet()){
                newDevices.addAll(newGateway.getValue());
            }
            
            if(accessibleDevices.size() == newDevices.size()){
                selectedGateways.remove(gatewayRemove1);
                selectedGateways.remove(gatewayRemove2);
                selectedGateways.add(gatewayInsert);
                mapDevices.remove(gatewayRemove1);
                mapDevices.remove(gatewayRemove2);
                mapDevices.put(gatewayInsert, devicesInsert);
                goodSelection = true;
                
                System.out.println(" >> Trocando gateway - " + gatewayRemove1.toString() + " , " 
                    + gatewayRemove2.toString() + " - por - " 
                    + gatewayInsert.toString());
            }
            else{
                goodSelection = false;
            }
            i++;
        }while(goodSelection || (i < MAX_ITER));
    }
    
}
