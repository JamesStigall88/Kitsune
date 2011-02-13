/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kitsune;

import java.util.ArrayList;

/**
 *
 * @author Guillaume
 */
public class FloydWarshall {
    private UndirectedGraph graph;
    private int[][] path;
    private int[][] next;
    private int size;
    private ArrayList itinerary;


    public FloydWarshall(UndirectedGraph g){
        this.graph= g;
        this.size=g.largestNode();

        this.itinerary= new ArrayList();

        this.buildAdjacencyMatrix();
        this.calculatePaths();
    }

    private void buildAdjacencyMatrix(){
        this.path= new int[this.size+1][this.size+1];

        for (int i=1;i<=this.size;i++){
            for (int j=1;j<=this.size;j++){
                if (this.graph.edgeExists(i,j)){
                    this.path[i][j]=1;
                } else{
                    this.path[i][j]=100000;
                }
            }
        }

        
    }

    private void calculatePaths(){
        this.next= new int[this.size+1][this.size+1];

        for (int k=1;k<=this.size;k++){
            for (int i=1;i<=this.size;i++){
                for (int j=1;j<=this.size;j++){
                    if (path[i][k]+path[k][j]<path[i][j]){
                        path[i][j]= path[i][k]+path[k][j];
                        next[i][j]= k;
                    }
                }
            }
        }
    }

    public int getPath(int i, int j){
        this.itinerary= new ArrayList();
        return this.calculatePath(i, j);

    }

    private int calculatePath(int i, int j){
        if (path[i][j]==100000){ //no edge between i and j
            return -1;
        }

        int intermediate= next[i][j];
        if (intermediate==0){
            itinerary.add((Integer)j);
            //System.out.println("Go from "+i+" to "+j);
            return 0;   //already an edge between i and j
        } else {
            return this.calculatePath(i,intermediate) + intermediate + calculatePath(intermediate,j);
        }
    }

    public void printPath(){
        for (int i=1;i<=this.size;i++){
            for (int j=1;j<=this.size;j++){
                System.out.print(this.path[i][j]+"|");
            }
            System.out.println();
        }
    }

    public ArrayList getItinerary(){
        return this.itinerary;
    }

    public UndirectedGraph getGraph(){
        return this.graph;
    }
}
