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

import java.awt.geom.Point2D;

/**
 *
 * @author Brenno Mello <brennodemello.bm at gmail.com>
 */
public class Coordinate {
    
    private final Point2D cord;
    
    /* Create a coordinate */
    public Coordinate(double latitude, double longitude){
        cord = new Point2D.Double(latitude, longitude);
    }
    
    public Coordinate(){
        this(0, 0);
    }
    
    public double getLatitude(){
        return cord.getX();
    }
    
    public double getLongitude(){
        return cord.getY();
    }
    
    public void setLatitude(double latitude){
        cord.setLocation(latitude, cord.getY());
    }
    
    public void setLongitude(double longitude){
        cord.setLocation(cord.getX(), longitude);
    }
 
    public void setLatitudeLongitude(double latitude, double longitude){
        cord.setLocation(latitude, longitude);
    }
    
    public Point2D getPoint(){
        return cord;
    }
    
    public boolean containsPoint(Point2D point){
        return (cord.distance(point) <= WifiConstraints.MAXIMUM_RANGE);
    }
    
}
