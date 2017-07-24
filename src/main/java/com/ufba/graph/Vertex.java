/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufba.graph;

/**
 *
 * @author brennomello
 */
public class Vertex {
   
   public char label;   // label (e.g. 'A')
   public boolean wasVisited;

   public Vertex(char lab)   // constructo
   {
      label = lab;
      wasVisited = false;
   
   }

     
}
