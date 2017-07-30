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

/**
 *
 * @author Brenno Mello <brennodemello.bm at gmail.com>
 */
public class Coordinate {
    
    public static final double EARTH_RADIUS = 6378.137;
    
    private double latitude;
    private double longitude;
    
    /* Create a coordinate */
    public Coordinate(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public Coordinate(){
        this(0, 0);
    }
    
    public double getLatitude(){
        return latitude;
    }
    
    public double getLongitude(){
        return longitude;
    }
    
    public void setLatitude(double latitude){
        this.latitude = latitude;
    }
    
    public void setLongitude(double longitude){
        this.longitude = longitude;
    }
 
    public void setLatitudeLongitude(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public boolean containsCoordinate(Coordinate deviceCoordinate) {
        double haversineDistanceKm = Haversine.distance(latitude, longitude, deviceCoordinate.getLatitude(), deviceCoordinate.getLongitude());
        double haversineDistanceM = haversineDistanceKm * 1000.0;
        return haversineDistanceM <= WifiConstraints.MAXIMUM_RANGE;
    }
    
}
