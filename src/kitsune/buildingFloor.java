package kitsune;

import java.awt.*;
import javax.swing.JPanel;
import java.util.Iterator;
import java.util.ArrayList;
import java.lang.Math;

public class buildingFloor extends JPanel{

    Graphics2D g;
    Environment loadedEnv;

    int linethickness = 3; //the thickness of a line in the UI
    int length = 28+1+this.linethickness; //the length (in pixels) of a unit in the UI
    private static final int size = 20; //the dimension of the square matrix
    private static final int frameOffset = 25; //the size of the margin in the view

    int level; //the level of this JPanel

    int hNode1, hNode2;

    public buildingFloor(Environment e, int l){
        this.loadedEnv = e;
        this.level= l;
        this.hNode1=0;
        this.hNode2=0;
    }


    @Override
    public void paintComponent(Graphics g){
        this.g=(Graphics2D)g;
        super.paintComponent(g);

        //draw grid
        for (int i=0;i<(this.size*this.size);i++){
                this.g.drawLine(getNodeX(i), getNodeY(i),getNodeX(i), getNodeY(i)); //by passing twice the same points we can draw a line
        }

        //Fire drawing
        Fire fire = this.loadedEnv.getLevel(this.level).getFire();

        int[][] fireArray = fire.getArray();
        for(int i=0;i<this.size;i++) {
            for(int j=0;j<this.size;j++) {
                if (fireArray[i][j]!=0)
                    this.drawFire(i*this.size+j,fireArray[i][j]);
            }
        }

        //Wall drawing
        UndirectedGraph walls = this.loadedEnv.getLevel(this.level).getWalls();

        Iterator it = walls.getGraph().keySet().iterator();
        while(it.hasNext()) {
            int tempNode = Integer.parseInt(it.next().toString());
            Iterator it2 = ((ArrayList)walls.getGraph().get(tempNode)).iterator();
            while (it2.hasNext()){
                int tempNode2= Integer.parseInt(it2.next().toString());
                this.drawWall(tempNode, tempNode2);
            }
        }


        //door drawing
        UndirectedGraph doors = this.loadedEnv.getLevel(this.level).getDoors();

        it = doors.getGraph().keySet().iterator();
        while(it.hasNext()) {
            int tempNode = Integer.parseInt(it.next().toString());
            Iterator it2 = ((ArrayList)doors.getGraph().get(tempNode)).iterator();
            while (it2.hasNext()){
                int tempNode2= Integer.parseInt(it2.next().toString());
                this.drawDoor(tempNode, tempNode2);
            }
        }

        //exit drawing
        UndirectedGraph exits = this.loadedEnv.getLevel(this.level).getExits();

        it = exits.getGraph().keySet().iterator();
        while(it.hasNext()) {
            int tempNode = Integer.parseInt(it.next().toString());
            Iterator it2 = ((ArrayList)exits.getGraph().get(tempNode)).iterator();
            while (it2.hasNext()){
                int tempNode2= Integer.parseInt(it2.next().toString());
                this.drawExit(tempNode, tempNode2);
            }
        }

        //Agent drawing
        ArrayList agents = this.loadedEnv.getAgents();
        it = agents.iterator();

        while(it.hasNext()){
            Agent a = (Agent)it.next();
            if (a.getLevel()==this.level)
                this.drawAgent(a);
        }

        //Label drawing
        ArrayList labels = this.loadedEnv.getLevel(this.level).getLabels();
        it = labels.iterator();

        while (it.hasNext()){
            Label l = (Label)it.next();
            this.drawLabel(l);
        }
        
        //highlight the edge on which the mouse is
        if (this.hNode1!=0 && this.hNode2!=0)
            this.drawHighlight();
   }

    public void highlight(int node1, int node2){
        this.hNode1= node1;
        this.hNode2= node2;
    }

    public int getHNode1(){
        return this.hNode1;
    }

    public int getHNode2(){
        return this.hNode2;
    }

    private void drawWall(int node1, int node2){ //draws a wall between 2 nodes
        g.setColor(Color.BLACK);
        this.g.setStroke(new BasicStroke(this.linethickness));
        this.g.drawLine(getNodeX(node1), getNodeY(node1), getNodeX(node2), getNodeY(node2));
        g.setColor(Color.BLACK);
    }

    private void drawDoor(int node1, int node2){ //draws a door between 2 nodes
        g.setColor(Color.PINK);
        this.g.setStroke(new BasicStroke(this.linethickness));
        this.g.drawLine(getNodeX(node1), getNodeY(node1), getNodeX(node2), getNodeY(node2));
        g.setColor(Color.BLACK);
    }

    private void drawExit(int node1, int node2){ //draws an exit between 2 nodes
        g.setColor(Color.GREEN);
        this.g.setStroke(new BasicStroke(this.linethickness));
        this.g.drawLine(getNodeX(node1), getNodeY(node1), getNodeX(node2), getNodeY(node2));
        g.setColor(Color.BLACK);
    }

    private void drawHighlight(){
        g.setColor(Color.LIGHT_GRAY);
        this.g.setStroke(new BasicStroke(this.linethickness));
        this.g.drawLine(getNodeX(this.hNode1), getNodeY(this.hNode1), getNodeX(this.hNode2), getNodeY(this.hNode2));
        g.setColor(Color.BLACK);
    }

    private void drawAgent(Agent a){
        int x=a.getX();
        int y=a.getY();

        g.setColor(a.getColor());
        this.g.drawOval(x, y, 6, 6);
        this.g.fillOval(x, y, 6, 6);
        g.setColor(Color.BLACK);
}

    private int getNodeX(int n){ //returns node N's X coordinate in the jpanel
        return (n%this.size)*this.length+this.frameOffset;
    }

    private int getNodeY(int n){ //returns node N's Y coordinate in the jpanel
        
        return ((int)(Math.floor(n/this.size)*this.length+this.frameOffset));
    }

    private int getSquareX(int n){ //returns the X coordinate of square n
        return this.getNodeX(n);
    }

    private int getSquareY(int n){ //returns the Y coordinate of square n
        return this.getNodeY(n);
    }

    private void drawFire(int square, int intensity){ //draws fire with intensity in square
        int x=getSquareX(square);
        int y=getSquareY(square);

        //The 6 colors for different level of fire
        Color colorLevel1 = new Color(255,255,0);
        Color colorLevel2 = new Color(255,204,0);
        Color colorLevel3 = new Color(255,153,0);
        Color colorLevel4 = new Color(255,102,0);
        Color colorLevel5 = new Color(255,51,0);
        Color colorLevel6 = new Color(255,0,0);

        Color[] fireColor = {null, Color.DARK_GRAY, colorLevel1, colorLevel2, colorLevel3, colorLevel4, colorLevel5, colorLevel6};
        Color squareColor = fireColor[intensity];

        g.setColor(squareColor); //to replace later by different color depending on intensity
        this.g.fillRect(x, y, this.length, this.length);        
        g.setColor(Color.BLACK);
    }

    private void drawLabel(Label l){
        this.g.drawString(l.getLabel(), l.getX(), l.getY());
    }

}