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
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Brenno Mello <brennodemello.bm at gmail.com>
 */
public class CoveringMatrix {
    
    private Map<Gateway, Set<Device>> coveringMatrix;
    private Set<Device> devicesUncovered;
    private Topology topology;
    
    private Set<Device> listDevices;
    private Set<Gateway> listGateways;
           
    public static int MAX_ITERACOES = 50;
    public static int MAX_NO_IMPROV = 50;
    public static double ALPHA_FACTOR = 50;
    
    public CoveringMatrix(int qtdGateways, int qtdDevice) throws Exception{
        // Generate a random matrix
        throw new Exception("This method is not complete!");
    }

    public CoveringMatrix(Topology topology){
        this.topology = topology;
        coveringMatrix = new HashMap<>();
        devicesUncovered = new HashSet<>();
        
        listDevices = topology.getListDevices();
        listGateways = topology.getListGateways();
        Map<Device, Boolean> devicesCovered = new HashMap<>();
        
        for(Device device : listDevices){
            devicesCovered.put(device, Boolean.FALSE);
        }
        
        for(Gateway gateway : listGateways){
            Set<Device> devicesNear = new HashSet<>();
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

    public Map<Gateway, Set<Device>> getCoveringMatrix() {
        return coveringMatrix;
    }

    public void setCoveringMatrix(Map<Gateway, Set<Device>> coveringMatrix) {
        this.coveringMatrix = coveringMatrix;
    }

    public Topology getTopology() {
        return topology;
    }

    public void setTopology(Topology topology) {
        this.topology = topology;
    }

    public Set<Device> getDevicesUncovered() {
        return devicesUncovered;
    }

    public void setDevicesUncovered(Set<Device> devicesUncovered) {
        this.devicesUncovered = devicesUncovered;
    }
    
    // TODO
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
    
    public Set<Gateway> greedyAlgorithm(){
        Set<Device> solutionDevice = new LinkedHashSet<>();
        Set<Gateway> solutionGateway = new HashSet<>();
        
        while(!solutionDevice.containsAll(listDevices)){
            Gateway daVez = findMinSet(coveringMatrix , solutionDevice);
            solutionDevice.addAll(coveringMatrix.remove(daVez));
            solutionGateway.add(daVez);
        }
        
        return solutionGateway;
    }
    
    public Gateway findMinSet(Map<Gateway, Set<Device>> gateways, 
        Set<Device> solutionDevices){
        
        Gateway gatewayMenorCusto = null;
        Float menorCusto = Float.MAX_VALUE;
        
        for(Map.Entry<Gateway, Set<Device>> gateway : gateways.entrySet()){
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
    
    
    public List<Device> getDiferenca(Set<Device> conjunto1, 
            Set<Device> conjunto2){
        
        List<Device> conjuntoResultado = new ArrayList<>();
        conjuntoResultado.addAll(conjunto1);
        conjuntoResultado.removeAll(conjunto2);
        
        /*
        for(Device d: conjunto1){
            if(!conjunto2.contains(d)){
                conjuntoResultado.add(d);
            }
        }
        */
        
        return conjuntoResultado;
    }
    
    public Gateway getMax(Map<Gateway, Integer> featureCosts){
           Gateway maxGateway = null;
           for (Map.Entry<Gateway, Integer> gateway : featureCosts.entrySet()) {
               if(maxGateway == null || gateway.getValue()>=featureCosts.get(maxGateway))
                   maxGateway = gateway.getKey();
           }
           
        return maxGateway;
    }
    
    public Gateway getMin(Map<Gateway, Integer> featureCosts){
        Gateway minGateway = null;
           for (Map.Entry<Gateway, Integer> gateway : featureCosts.entrySet()) {
               if(minGateway == null || gateway.getValue()>=featureCosts.get(minGateway))
                   minGateway = gateway.getKey();
           }
           
        return minGateway;
    }
    
    public Best greedyRandomizedConstruction(){
        Random randomNumber = new Random();
        
        Set<Device> candidateDevices = new HashSet<>();
        Set<Gateway> candidatesGateway = new HashSet<>();        
        Map<Gateway, Set<Device>> candidateMapDevices = new HashMap<>();
        
        //while(!candidateDevices.containsAll(listDevices)){
        while(candidateDevices.size() < listDevices.size()){
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
                if(gateway.getValue() <= featureCosts.get(min) + ALPHA_FACTOR * (featureCosts.get(min) - featureCosts.get(max)))
                    candidateRclGateway.add(gateway.getKey());
            }
            
            Gateway randomCandidate = candidateRclGateway.get(randomNumber.nextInt(candidateRclGateway.size()));
            Set<Device> randomCandidateDevices = coveringMatrix.get(randomCandidate);
            candidatesGateway.add(randomCandidate);
            candidateDevices.addAll(randomCandidateDevices);
            candidateMapDevices.put(randomCandidate, randomCandidateDevices);
        }
        
        Best candidate = new Best(candidatesGateway, candidateDevices, candidateMapDevices);
                
        return candidate;
    }
    
   public Best localSearch(Best best){
       while(best.tryRemoveOne()){}
       
       return best;
   }
    
    public Best grasp(){
        Best best = null;
        
        for(int i = 0; i < MAX_ITERACOES; i++){
            Best candidate = greedyRandomizedConstruction();
            candidate = localSearch(candidate);
            if(best == null || best.getQtdGateways() < candidate.getQtdGateways()){
                best = candidate;
            }
            System.out.println(" > Iteration " + (i + 1) + ", best=" + best.getQtdGateways());
        }
 
        return best;
    }
    
    public void printCoveringMatrixAsMatrix(){        
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
