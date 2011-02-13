package kitsune;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * This class corresponds to the representation of a single level in the building.
 * @author Guillaume
 */
public class Level  implements Serializable {
    private static final int size = 20; //the dimension of the matrix
    private UndirectedGraph walls; //a matrix representing the walls as an undirected graph
    private UndirectedGraph doors; //a matrix representing the doors as an undirected graph
    private UndirectedGraph exits; //a matrix representing the exits as an undirected graph

    private ArrayList labels; //the labels present on the current floor

    private Fire fire; //the fire on this level

    private UndirectedGraph pathGraph; //graph containing all the possible paths within the building
    private ArrayList fireExits; //list of all the squares in the graph containing an exit


    // Public methods
    ////////////////////////////////
    public Level(){
        this.walls = new UndirectedGraph();
        this.doors = new UndirectedGraph();
        this.exits = new UndirectedGraph();

        this.labels= new ArrayList();

        this.fire = new Fire(this);
        this.fireExits= new ArrayList();
    }

    public void selectWall(int node1, int node2){
        if (!this.doors.edgeExists(node1, node2) && !this.exits.edgeExists(node1, node2)){ //we check that there is no door on the current edge
            if (this.walls.edgeExists(node1, node2)){ //if the wall already exists...
                this.removeWall(node1, node2); //we remove it
            } else if(node1!=0 && node2!=0) {
                this.addWall(node1, node2); //else we add it
            }
        }
    }

    public void clearWalls(){
        this.walls.clearEdges();
    }

    private void addWall(int node1, int node2){
        this.walls.addEdge(node1, node2);
    }

    private void removeWall(int node1, int node2){
        this.walls.removeEdge(node1, node2);
    }

    public void selectDoor(int node1, int node2){
       if (!this.walls.edgeExists(node1, node2) && !this.exits.edgeExists(node1, node2)){ //we check that there is no wall on the current edge
            if (this.doors.edgeExists(node1, node2)){
                this.removeDoor(node1, node2);
            } else if(node1!=0 && node2!=0) {
                this.addDoor(node1, node2);
            }
       }
    }

    public void selectExit(int node1, int node2){
       if (!this.walls.edgeExists(node1, node2) && !this.doors.edgeExists(node1, node2)){ //we check that there is no wall on the current edge
            if (this.exits.edgeExists(node1, node2)){
                this.removeExit(node1, node2);
            } else if(node1!=0 && node2!=0) {
                this.addExit(node1, node2);
            }
       }
    }

    public void clearDoors(){
        this.doors.clearEdges();
    }

    private void addDoor(int node1, int node2){
        this.doors.addEdge(node1, node2);
    }

    private void removeDoor(int node1, int node2){
        this.doors.removeEdge(node1, node2);
    }

    private void addExit(int node1, int node2){
        this.exits.addEdge(node1, node2);
    }

    private void removeExit(int node1, int node2){
        this.exits.removeEdge(node1, node2);
    }

    public UndirectedGraph getWalls(){
        return this.walls;
    }

    public UndirectedGraph getDoors(){
        return this.doors;
    }

    public int getNumDoors(){
        return this.doors.size()/2;
    }

    public int getNumExits(){
        return this.exits.size()/2;
    }

    public UndirectedGraph getExits(){
        return this.exits;
    }

    public Fire getFire(){
        return this.fire;
    }

    public ArrayList getLabels(){
        return this.labels;
    }

    public void insertLabel(int x, int y, String text){
        Label l = new Label(x,y,text);
        this.labels.add(l);
    }

    public void removeLabel(int n){
        this.labels.remove(n);
    }

    //wall detectors
    public boolean hasRightWall(int n){ //returns true if the given cell has a right wall
        int node=n;//+(int)(Math.floor(n/this.size)); //we first calculate the square's top left node index
        node= node+1; //then the square's top right hand corner node index

        if (this.walls.edgeExists(node,node+this.size)){ //check for a wall
            return true;
        }
        return false;
    }

    public boolean hasLeftWall(int n){ //returns true if the given cell has a left wall
        int node=n;//+(int)(Math.floor(n/this.size)); //we first calculate the square's top left node index

        if (this.walls.edgeExists(node,node+this.size)){ //check for a wall
            return true;
        }
        return false;
    }

    public boolean hasTopWall(int n){ //returns true if the given cell has a top wall
        int node=n;//+(int)(Math.floor(n/this.size)); //we first calculate the square's top left node index

        if (this.walls.edgeExists(node,node+1)){ //check for a wall
            return true;
        }
        return false;
    }

    public boolean hasBottomWall(int n){ //returns true if the given cell has a bottom wall
        int node=n;//+(int)(Math.floor(n/this.size)); //we first calculate the square's top left node index
        node= node+this.size; //then the square's top right hand corner node index

        if (this.walls.edgeExists(node,node+1)){ //check for a wall
            return true;
        }
        return false;
    }

    //fire exit detectors
    public boolean hasRightExit(int n){ //returns true if the given cell has a right wall
        int node=n;//+(int)(Math.floor(n/this.size)); //we first calculate the square's top left node index
        node= node+1; //then the square's top right hand corner node index

        if (this.exits.edgeExists(node,node+this.size)){ //check for a wall
            return true;
        }
        return false;
    }

    public boolean hasLeftExit(int n){ //returns true if the given cell has a left wall
        int node=n;//+(int)(Math.floor(n/this.size)); //we first calculate the square's top left node index

        if (this.exits.edgeExists(node,node+this.size)){ //check for a wall
            return true;
        }
        return false;
    }

    public boolean hasTopExit(int n){ //returns true if the given cell has a top wall
        int node=n;//+(int)(Math.floor(n/this.size)); //we first calculate the square's top left node index

        if (this.exits.edgeExists(node,node+1)){ //check for a wall
            return true;
        }
        return false;
    }

    public boolean hasBottomExit(int n){ //returns true if the given cell has a bottom wall
        int node=n;//+(int)(Math.floor(n/this.size)); //we first calculate the square's top left node index
        node= node+this.size; //then the square's bottom left node index

        if (this.exits.edgeExists(node,node+1)){ //check for a wall
            return true;
        }
        return false;
    }

    public boolean hasExit(int n){
        return hasRightExit(n) || hasLeftExit(n) || hasTopExit(n) || hasBottomExit(n);
    }

    //builds itinerary graph
    public void buildGraph(int n){ //builds itinerary graph from starting square n
        this.pathGraph= new UndirectedGraph();
        this.fireExits= new ArrayList();

        ArrayList processedSquares= new ArrayList();
        ArrayList unprocessedSquares= new ArrayList();
        UndirectedGraph graph= new UndirectedGraph();

        unprocessedSquares.add((Integer)n);

        //System.out.println("Let's build the graph!");
        //System.out.println("Starting at square "+n);

        while (!unprocessedSquares.isEmpty()){
        //System.out.println("Still "+unprocessedSquares.size()+" squares to process!");

        n=(Integer)unprocessedSquares.get(0);
            graph.addNode(n);
            if (!hasRightWall(n) && !hasRightExit(n)){
                if (!graph.nodeExists(n+1)){
                    graph.addNode(n+1);
                }
                graph.addEdge(n,n+1);
                if (!unprocessedSquares.contains((Integer)n+1) && !processedSquares.contains((Integer)n+1))
                    unprocessedSquares.add((Integer)n+1);
            }

            if (!hasLeftWall(n) && !hasLeftExit(n)){
                if (!graph.nodeExists(n-1)){
                    graph.addNode(n-1);
                }
                graph.addEdge(n,n-1);
                if (!unprocessedSquares.contains((Integer)n-1) && !processedSquares.contains((Integer)n-1))
                    unprocessedSquares.add((Integer)n-1);
            }

            if (!hasTopWall(n) && !hasTopExit(n)){
                if (!graph.nodeExists(n-this.size)){
                    graph.addNode(n-this.size);
                }
                graph.addEdge(n,n-this.size);
                if (!unprocessedSquares.contains((Integer)n-this.size) && !processedSquares.contains((Integer)n-this.size))
                    unprocessedSquares.add((Integer)n-this.size);
            }

            if (!hasBottomWall(n) && !hasBottomExit(n)){
                if (!graph.nodeExists(n+this.size)){
                    graph.addNode(n+this.size);
                }
                graph.addEdge(n,n+this.size);
                if (!unprocessedSquares.contains((Integer)n+this.size) && !processedSquares.contains((Integer)n+this.size))
                    unprocessedSquares.add((Integer)n+this.size);
            }
            unprocessedSquares.remove(0); //the first unprocessed squares was processed, so we remove it...
            processedSquares.add((Integer)n);//...and we add it here

            if (hasExit(n) && !this.fireExits.contains((Integer)n)){
                this.fireExits.add((Integer)n);
            }

            //System.out.println("Processed squares: "+processedSquares);
            //System.out.println("Unprocessed squares: "+unprocessedSquares);
            //System.out.println("Fire exits at: "+this.fireExits);
        }

        //System.out.println("Graph calculated");

        this.pathGraph=graph;
        //this.pathGraph.printGraph();
    }

    public UndirectedGraph getPathGraph(){
        return this.pathGraph;
    }

    public ArrayList getFireExits(){
        return this.fireExits;
    }
}