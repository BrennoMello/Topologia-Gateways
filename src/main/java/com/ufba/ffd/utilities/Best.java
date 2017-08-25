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

import com.ufba.ffd.entities.Gateway;
import java.util.Set;

/**
 *
 * @author brenno
 */
public class Best {
    private int qtdGateways;
    private int qtdDevices;
    private Set<Gateway> selectGateways;

    public int getQtdGateways() {
        return qtdGateways;
    }

    public void setQtdGateways(int qtdGateways) {
        this.qtdGateways = qtdGateways;
    }

    public int getQtdDevices() {
        return qtdDevices;
    }

    public void setQtdDevices(int qtdDevices) {
        this.qtdDevices = qtdDevices;
    }

    public Set<Gateway> getSelectGateways() {
        return selectGateways;
    }

    public void setSelectGateways(Set<Gateway> selectGateways) {
        this.selectGateways = selectGateways;
        setQtdGateways(selectGateways.size());
    }
    
    public void containsGateways(){
        
    }
    
}
