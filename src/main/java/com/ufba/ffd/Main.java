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
package com.ufba.ffd;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ufba.ffd.entities.Topology;
import com.ufba.ffd.utilities.Coordinate;
import com.ufba.ffd.utilities.CoordinateDeserializer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Brenno Mello <brennodemello.bm at gmail.com>
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        InputStream arquivo = Main.class.getResourceAsStream("/br/ufba/ffd/config/basic_topology.json");
        
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(Coordinate.class, new CoordinateDeserializer());
        Gson gson = gb.create();
        
        BufferedReader buff_arquivo = new BufferedReader(new InputStreamReader(arquivo));
        Topology topolgy = gson.fromJson(buff_arquivo, Topology.class);
        
        System.out.print(topolgy);
    }
}
