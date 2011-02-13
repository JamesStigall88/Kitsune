package kitsune;

import java.awt.*;
import java.io.Serializable;

/**
 * @author Guillaume
 */
public class Agent implements Serializable {
    public int linethickness = 3; //the thickness of a line in the UI
    public int length = 28+1+this.linethickness; //the length (in pixels) of a unit in the UI
    public static final int size = 20; //the dimension of the square matrix
    public static final int frameOffset = 25; //the size of the margin in the view

    
    private int x;
    private int y;

    private int level;
    private boolean exited;
    private long exitedTime;

    private Environment parent;
    private Color color;
    private Behavior behavior;

    public int speed=2;
    private int life=500;

    public Agent(int x, int y, int l, String s, Environment parent){
        this.x=x;
        this.y=y;
        this.level=l;
        this.exited= false;
        this.exitedTime=0;
        this.parent= parent;

        if (s.equals("panic"))
            this.behavior= new PanicBehavior(this);
        else if (s.equals("follower"))
            this.behavior= new FollowerBehavior(this);
        else if (s.equals("optimal"))
            this.behavior= new OptimalBehavior(this);

    }

    public void step(){
        int i=(int)Math.floor((this.x-this.frameOffset)/this.length);
        int j=(int)Math.floor((this.y-this.frameOffset)/this.length);
        int fire=(this.parent.getLevel(level).getFire().getArray())[j][i];

        this.diminishLife(fire);

        if (!this.exited && this.isAlive())
            this.behavior.step();
    }

    public void exit(){
        this.exited=true;
        this.exitedTime=this.parent.getSimTime();
    }

    //Getters

    public int getLevel(){
        return this.level;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public Color getColor(){

        if (this.isAlive())
            return this.behavior.color;
        return Color.white;
    }

    public long getExitTime(){
        return this.exitedTime;
    }

    public boolean hasExited(){
        return this.exited;
    }

    public int getSquare(){ //gets the index of the square the agent is on
        int nodex=(int)Math.floor((this.x+8)/this.length)-1;
        int nodey=(int)Math.floor((this.y+8)/this.length)-1;
        int square=nodex+(nodey*this.size);
        return square;
    }

    public Environment getParent(){
        return this.parent;
    }

    public int getLife(){
        return this.life;
    }

    public boolean isAlive(){
        if (this.getLife()<1){
            return false;
        }
        return true;
    }

    //Setters
    public void setX(int x){
        this.x=x;
    }

    public void setY(int y){
        this.y=y;
    }

    public void setSpeed(int s){
        this.speed=s;
    }

    public boolean isStuck(){
        return this.behavior.stuck || !this.isAlive();
    }

    public void diminishLife(int d){
        this.life-=d;
    }

    public Behavior getBehavior(){
        return this.behavior;
    }
}
