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
public class Graph {
   private final int MAX_VERTS = 20;
   private Vertex vertexList[]; // list of vertices
   private int adjMat[][];      // adjacency matrix
   private int nVerts;          // current number of vertices
   //private Queue theQueue;

   public Graph() {
      vertexList = new Vertex[MAX_VERTS];
                                          // adjacency matrix
      adjMat = new int[MAX_VERTS][MAX_VERTS];
      nVerts = 0;
      for(int j=0; j<MAX_VERTS; j++)      // set adjacency
         for(int k=0; k<MAX_VERTS; k++)   //    matrix to 0
            adjMat[j][k] = 0;
    //theQueue = new Queue();
    
   }  // end constructor

   public void addVertex(char lab){
      vertexList[nVerts++] = new Vertex(lab);
    
   }

   public void addEdge(int start, int end){
      adjMat[start][end] = 1;
      adjMat[end][start] = 1;
   }

   public void displayVertex(int v) {
      System.out.print(vertexList[v].label);
   }

}
