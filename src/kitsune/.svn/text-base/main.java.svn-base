/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kitsune;

/**
 *
 * @author Guillaume
 */
public class main {
    public static void main(String [ ] args){
        UndirectedGraph graph= new UndirectedGraph();
        graph.addEdge(124, 12);
        graph.addEdge(13, 42);
        graph.addEdge(12, 13);
        graph.addEdge(124, 125);
        graph.addEdge(112, 11);
        graph.addEdge(12, 16);
        graph.addEdge(42, 16);
        graph.addEdge(12, 125);
        graph.addEdge(13, 124);
        graph.addEdge(42, 112);
        graph.addEdge(112, 13);

        FloydWarshall fw= new FloydWarshall(graph);

        int src=125;
        int dst=112;

        fw.getPath(src,dst);
    }

}
