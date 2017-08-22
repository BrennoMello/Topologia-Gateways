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
package com.ufba.ffd.coveringmatrix;

import com.ufba.ffd.entities.Device;
import com.ufba.ffd.entities.Gateway;
import com.ufba.ffd.entities.Topology;
import com.ufba.ffd.utilities.Best;
import com.ufba.ffd.utilities.Coordinate;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Brenno Mello <brennodemello.bm at gmail.com>
 */
public class CoveringMatrix {
    
    private Map<Gateway, List<Device>> coveringMatrix;
    private List<Device> devicesUncovered;
    private Topology topology;
    List<Device> listDevices;
    List<Gateway> listGateways;
    
    public CoveringMatrix(){
        dump();
    }
    
    
    
    public CoveringMatrix(int qtdGateways, int qtdDevice){
        
        for (int i = 0; i < 10; i++) {
            
        }
        
        
        for (int i = 0; i < qtdGateways; i++) {
            Gateway gateway = new Gateway();
            List<Device> devices = new ArrayList<>();
            for (int j = 0; j < qtdDevice; j++) {
                Device device = new Device();
                devices.add(device);
            }
            coveringMatrix.put(gateway, devices);
        }
    }
    
    public void dump(){
        coveringMatrix = new HashMap<>();
        topology = new Topology();
        
        Device dev1 = new Device();
        Device dev2 = new Device();
        Device dev3 = new Device();
        Device dev4 = new Device();
        Device dev5 = new Device();
        Device dev6 = new Device();
        Device dev7 = new Device();
        Device dev8 = new Device();
        Device dev9 = new Device();
        Device dev10 = new Device();
        Device dev11 = new Device();
        Device dev12 = new Device();
        Device dev13 = new Device();
        Device dev14 = new Device();
        Device dev15 = new Device();
        Device dev16 = new Device();
        Device dev17 = new Device();
        
        
        Gateway gat1 = new Gateway();
        Gateway gat2 = new Gateway();
        Gateway gat3 = new Gateway();
        Gateway gat4 = new Gateway();
        Gateway gat5 = new Gateway();
        
        
        List<Device> devices1 = new ArrayList<>();
        devices1.add(dev10);
        devices1.add(dev7);
        devices1.add(dev8);
        devices1.add(dev9);
        devices1.add(dev12);
        devices1.add(dev17);
        
        List<Device> devices2 = new ArrayList<>();
        devices2.add(dev1);
        devices2.add(dev5);
        devices2.add(dev6);
        devices2.add(dev7);
        devices2.add(dev8);
        devices2.add(dev9);
        devices2.add(dev10);
        
        List<Device> devices3 = new ArrayList<>();
        devices3.add(dev11);
        devices3.add(dev12);
        devices3.add(dev13);
        devices3.add(dev14);
        devices3.add(dev15);
        
        List<Device> devices4 = new ArrayList<>();
        devices4.add(dev7);
        devices4.add(dev5);
        devices4.add(dev17);
        devices4.add(dev16);
        devices4.add(dev1);
        
        List<Device> devices5 = new ArrayList<>();
        devices5.add(dev4);
        devices5.add(dev8);
        devices5.add(dev3);
        devices5.add(dev13);
        devices5.add(dev2);
        
        List<Device> allDevices = new ArrayList<>();
        List<Gateway> allGateways = new ArrayList<>();
        allDevices.add(dev1);
        allDevices.add(dev2);
        allDevices.add(dev3);
        allDevices.add(dev4);
        allDevices.add(dev5);
        allDevices.add(dev6);
        allDevices.add(dev7);
        allDevices.add(dev8);
        allDevices.add(dev9);
        allDevices.add(dev10);
        allDevices.add(dev11);
        allDevices.add(dev12);
        allDevices.add(dev13);
        allDevices.add(dev14);
        allDevices.add(dev15);
        allDevices.add(dev16);
        allDevices.add(dev17);
        
        
        allGateways.add(gat1);
        allGateways.add(gat2);
        allGateways.add(gat3);
        allGateways.add(gat4);
        allGateways.add(gat5);
        
        topology.setListDevices(allDevices);
        topology.setListGateways(allGateways);
        
        coveringMatrix.put(gat1, devices1);
        coveringMatrix.put(gat2, devices2);
        coveringMatrix.put(gat3, devices3);
        coveringMatrix.put(gat4, devices4);
        coveringMatrix.put(gat5, devices5);
        
        
    }
    
    public CoveringMatrix(Topology topology){
        this.topology = topology;
        coveringMatrix = new HashMap<>();
        devicesUncovered = new ArrayList<>();
        
        listDevices = topology.getListDevices();
        listGateways = topology.getListGateways();
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
    
    public Best naive(Map<Gateway, List<Device>> gateways){
        
        Best best = null;
        if (gateways.size() == 1) {
            best = new Best();
            best.setSelectGateways(gateways.keySet());
        }
        //List<Gateway> minSolution = new ArrayList<>();
        //List<Device> currentSolution = new ArrayList<>();
        Map<Gateway, List<Device>> selectGateways = null;
        
        
        for(Map.Entry<Gateway, List<Device>> gateway : gateways.entrySet()){
            selectGateways.putAll(gateways);
            selectGateways.remove(gateway);
            
            for (Map.Entry<Gateway, List<Device>> element : selectGateways.entrySet()) {
                Best getBest = naive(selectGateways);
                /*
                if(getBest.getGateways()<best.getGateways() || best == null){
                    best = getBest;
                }
                */    
            }
        }
        
        return best;
    }
    
    
    public List<Device> minCCChvatal(List<Device> E, Map<Gateway, List<Device>> coveringMatrix){
        
        
        
        
        return null;
        
        
        
        
    }
    
    public List<Gateway> greedyAlgorithm(){
        Set<Device> solutionDevice = new LinkedHashSet<>();
        List<Gateway> solutionGateway = new ArrayList<>();
        
        
        while(!solutionDevice.containsAll(listDevices)){
            
            Gateway daVez = findMinSet(coveringMatrix , solutionDevice);
            solutionDevice.addAll(coveringMatrix.remove(daVez));
            solutionGateway.add(daVez);
            
            
        }
        return solutionGateway;
    }
    
    public Gateway findMinSet(Map<Gateway, List<Device>> gateways, 
        Set<Device> solutionDevices){
        
        Gateway gatewayMenorCusto = null;
        Float menorCusto = Float.MAX_VALUE;
        
        for(Map.Entry<Gateway, List<Device>> gateway : gateways.entrySet()){
            int tamanhoDiferenca = getDiferenca(gateway.getValue(), solutionDevices).size(); 
            
            if (tamanhoDiferenca==0)
                continue;
            float custo = 1/tamanhoDiferenca;
          
            if(custo < menorCusto){
                menorCusto = custo;
                gatewayMenorCusto = gateway.getKey();
            }
        }
        
        return gatewayMenorCusto;
    }
    
    
    public List<Device> getDiferenca(List<Device> conjunto1, 
            Set<Device> conjunto2){
        
        List<Device> conjuntoResultado = new ArrayList<>();
        
        for(Device d: conjunto1){
            if(!conjunto2.contains(d)){
                conjuntoResultado.add(d);
            }
        }
        
        return conjuntoResultado;
    }
   
    public void grasp(){
        
        
        
        
        for (Gateway gateway : topology.getListGateways()) {
            List<Device> listDevice = coveringMatrix.get(gateway);
            System.out.println(gateway);
            for (Device device : listDevice) {
                System.out.println(device);
            }
        }
 
        
    }
    
    
    public void printCoveringMatrixAsMatrix(){
        List<Device> listDevices = topology.getListDevices();
        List<Gateway> listGateways = topology.getListGateways();
        
        System.out.format("%19s", "");
        for(Gateway gat : listGateways){
            System.out.format("%19s", gat.getName());
        }
        System.out.println();
        
        for(Device dev : listDevices){
            System.out.format("%19s", dev.getName());
            for(Gateway gat : listGateways){
                System.out.format("%19d", coveringMatrix.get(gat).contains(dev) ? 1 : 0);
            }
            System.out.println();
        }
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
        /*
        ret = ret.concat("\tUncovered => {\n");
        for(Device uncoveredDevice : devicesUncovered){
            ret = ret.concat("\t\t" + uncoveredDevice.getName() + ",\n");
        }
        ret = ret.concat("\t}\n");
        */
        ret = ret.concat("]");
        
        return ret;
    }

}
