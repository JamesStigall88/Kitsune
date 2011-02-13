package kitsune;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.Serializable;
import java.util.Collections;

/**
 * This class allows the representation of an undirected graph
 * @author Guillaume
 */
public class UndirectedGraph implements Serializable{
    private HashMap nodes; //the internal representation of the graph

    public UndirectedGraph(){
        this.nodes= new HashMap();
    }

    public void addEdge(int IDnode1, int IDnode2){ //adds an edge between nodes with the given id
    //we add node2 in the listing for node1
        if (!nodeExists(IDnode1))
            this.addNode(IDnode1);
        if (!this.edgeExists(IDnode1, IDnode2))
            ((ArrayList)nodes.get(IDnode1)).add(IDnode2);

    //and then we add node1 in the listing for node2
        if (!nodeExists(IDnode2))
            this.addNode(IDnode2);
        if (!this.edgeExists(IDnode2, IDnode1))
            ((ArrayList)nodes.get(IDnode2)).add(IDnode1);

    }

    public void removeEdge(int IDnode1, int IDnode2){ //removes an edge between the nodes with the given id
    //we remove the node2 from node1's listing
    if (edgeExists(IDnode1, IDnode2))
        ((ArrayList)nodes.get(IDnode1)).remove(((ArrayList)nodes.get(IDnode1)).indexOf(IDnode2)); //then we remove node2

    //we remove the node1 from node2's listing
    if (edgeExists(IDnode2, IDnode1))
        ((ArrayList)nodes.get(IDnode2)).remove(((ArrayList)nodes.get(IDnode2)).indexOf(IDnode1)); //then we remove node1

    //this checks if the undirected graph has any empty entries, and if so deletes them
    this.integrityCheck();
    }

    public void addNode(int IDnode){ //adds a node with the given id
        if (!this.nodeExists(IDnode)){
            ArrayList list = new ArrayList();
            nodes.put(IDnode, list);
        }
    }

    public void removeNode(int IDnode){ //removes node with the given id
        //first, we search for the presence of the node in every line and delete it
        Iterator it = nodes.keySet().iterator();
        while(it.hasNext()) {
            int tempNode = Integer.parseInt(it.next().toString());
            if (edgeExists(tempNode,IDnode))
                removeEdge(tempNode,IDnode);
        }

        //then we delete the node's entry itself
        nodes.remove(IDnode);

        //this checks if the undirected graph has any empty entries, and if so deletes them
        this.integrityCheck();
    }

    private void integrityCheck(){
        ArrayList toDelete= new ArrayList();

        //we can't directly delete nodes while we're iterating over them, so we'll have to create a list of nodes to delete
        Iterator it = nodes.keySet().iterator();
        while(it.hasNext()) {
            int tempNode = Integer.parseInt(it.next().toString());
            // if node has no entries, we mark node for deletion
            if (((ArrayList)nodes.get(tempNode)).isEmpty())
                toDelete.add(tempNode);
        }

        //we delete all the nodes marked for deletion
        it = toDelete.iterator();
        while (it.hasNext()){
            int tempNode = Integer.parseInt(it.next().toString());
            nodes.remove(tempNode);
        }
    }

    public boolean nodeExists(int IDnode){ //returns true if the node with given ID exists
        return nodes.containsKey(IDnode);
    }

    public boolean edgeExists(int IDnode1, int IDnode2){
        if (nodes.containsKey(IDnode1))
            if (((ArrayList)nodes.get(IDnode1)).contains(IDnode2))
                return true;
        return false;
    }

    public void clearEdges(){
        this.nodes=new HashMap();
    }

    public int largestNode(){
        return ((Integer)Collections.max(nodes.keySet())).intValue();
    }

/**
*
* @return  the adjacency list representing the graph
*/
    public HashMap getGraph(){
        return this.nodes;
    }

    public void printGraph(){
        System.out.println(nodes.toString());
    }

    public int size(){
        Iterator it = nodes.keySet().iterator();
        return this.nodes.size();
    }

}
