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

import com.ufba.ffd.utilities.Coordinate;

/**
 *
 * @author Brenno Mello <brennodemello.bm at gmail.com>
 */
public class FisicalDevice {
    private String name;
    private Coordinate coordinates;
    
    public FisicalDevice(String name, Coordinate cord){
        this.name = name;
        this.coordinates = cord;
    }

    public FisicalDevice(){
        this("", new Coordinate());
    }

    public Coordinate getCoordinate() {
        return coordinates;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinates = coordinate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString(){
        return ("{ Name => " + name + ", Coordinates => " + coordinates.getLatitude() + ";" + coordinates.getLongitude() + " }");
    }
}
