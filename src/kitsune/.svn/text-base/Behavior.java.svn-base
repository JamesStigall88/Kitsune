package kitsune;

import java.awt.*;

import java.io.Serializable;

/**
 *
 * @author Guillaume
 */
public class Behavior implements Serializable {
    public String type;
    public Color color;

    protected Agent parent;

    protected int squareTarget; //the square target
    protected int nextSquare; //the next square to go to
    protected int currentSquare; //the next square to go to
    protected int stepsSinceTarget;
    protected boolean isExiting;
    public boolean stuck;

    public Behavior(){
        type="none";
    }

    public void step(){
         
    }

    protected void stepToSquare(){
        if (this.nextSquare==this.currentSquare+1){ //if the next square is to the right
            this.parent.setX(this.parent.getX()+this.parent.speed);
        } else if (this.nextSquare==this.currentSquare-1) { //if the next square is to the left
            this.parent.setX(this.parent.getX()-this.parent.speed);
        } else if (this.nextSquare==this.currentSquare-this.parent.size) { //if the next square is up
            this.parent.setY(this.parent.getY()-this.parent.speed);
        } else if (this.nextSquare==this.currentSquare+this.parent.size) { //if the next square is down
            this.parent.setY(this.parent.getY()+this.parent.speed);

        //DIAGONALS
        }else if (this.nextSquare==this.currentSquare-this.parent.size-1) { //if the next square is to the up-left
            this.parent.setX(this.parent.getX()-this.parent.speed/2);
            this.parent.setY(this.parent.getY()-this.parent.speed/2);
        } else if (this.nextSquare==this.currentSquare-this.parent.size+1) { //if the next square is up-right
            this.parent.setX(this.parent.getX()+this.parent.speed/2);
            this.parent.setY(this.parent.getY()-this.parent.speed/2);
        } else if (this.nextSquare==this.currentSquare+this.parent.size-1) { //if the next square is down-left
            this.parent.setX(this.parent.getX()-this.parent.speed/2);
            this.parent.setY(this.parent.getY()+this.parent.speed/2);
        }   else if (this.nextSquare==this.currentSquare+this.parent.size+1) { //if the next square is down-right
            this.parent.setX(this.parent.getX()+this.parent.speed/2);
            this.parent.setY(this.parent.getY()+this.parent.speed/2);
        }
    }

    protected void calculateNextSquare(){

    }

    public String getType(){
        return this.type;
    }
}
