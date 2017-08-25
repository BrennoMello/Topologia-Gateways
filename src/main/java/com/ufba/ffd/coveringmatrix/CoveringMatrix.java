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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
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
        
        Set<Gateway> gateways_list = new HashSet<>(gateways.keySet());
        
        for(int qtd_el = 1; qtd_el <= gateways.size(); qtd_el++){
            for(int ind = 0; ind < (gateways.size() + 1 - qtd_el) ; ind++){
                Set<Gateway> gateways_selected = new HashSet<>();
                for(int n_assoc = 0; n_assoc < qtd_el; n_assoc++){
                    gateways_selected.clear();
                    
                }
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
    
    public Gateway getMax(Map<Gateway, Integer> featureCosts){
           Gateway maxGateway = null;
           for (Map.Entry<Gateway, Integer> gateway : featureCosts.entrySet()) {
               if(gateway.getValue()>=featureCosts.get(maxGateway))
                   maxGateway = gateway.getKey();
           }
           
        return maxGateway;
    }
    
    public Gateway getMin(Map<Gateway, Integer> featureCosts){
        Gateway minGateway = null;
           for (Map.Entry<Gateway, Integer> gateway : featureCosts.entrySet()) {
               if(gateway.getValue()>=featureCosts.get(minGateway))
                   minGateway = gateway.getKey();
           }
           
        return minGateway;
    }
    
    public List<Gateway> GreedyRandomizedConstruction(int alpha){
        
        Set<Device> candidateDevices = new LinkedHashSet<>();
        List<Gateway> candidatesGateway = new ArrayList<>();
        //Map<Gateway, List<Device>> coveringMatrix;
        
        
        while(!candidateDevices.containsAll(listDevices)){
            Map<Gateway, Integer> featureCosts = new LinkedHashMap<>();  
            List<Gateway> candidateRclGateway = new ArrayList<>();
            
            List<Gateway> restGateway = new ArrayList<>();
            restGateway.addAll(listGateways);
            restGateway.removeAll(candidateRclGateway);
            
            for (Gateway gat : restGateway) {
                featureCosts.put(gat, getDiferenca(coveringMatrix.get(gat), candidateDevices).size());
            }
            Gateway min = getMin(featureCosts);
            Gateway max = getMax(featureCosts);
            
            for (Map.Entry<Gateway, Integer> gateway : featureCosts.entrySet()) {
                if(gateway.getValue()<=featureCosts.get(min)+alpha*(featureCosts.get(min)-featureCosts.get(max)))
                    candidateRclGateway.add(gateway.getKey());
            }
            
            candidatesGateway.add(candidateRclGateway.get(alpha));
            
            
        }
        
        return null;
    }
    
   public List<Gateway> LocalSearch(List<Gateway> candidateRcl){
       List<Gateway> listSearch = new ArrayList<>();
       
       return listSearch;
   }
    
    public void grasp(){
        List<Gateway> solutionGateway = new ArrayList<>();
        List<Device> solutionDevices = new ArrayList<>();
        
         
        
        while(solutionDevices.containsAll(listDevices)){
            
            List<Gateway> candidateRcl = GreedyRandomizedConstruction(1);
            List<Gateway> listSearch = LocalSearch(candidateRcl);
            
            
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
