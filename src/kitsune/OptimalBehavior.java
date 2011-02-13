/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kitsune;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Guillaume
 */
public class OptimalBehavior extends Behavior implements Serializable{

    private boolean calculated=false;

    private ArrayList itinerary;

    public OptimalBehavior(Agent parent){
        this.type="optimal";
        this.color=color.BLUE;
        this.parent=parent;
    }

    @Override
    public void step(){
        if (this.isExiting && this.stepsSinceTarget==this.parent.length){
            this.parent.exit();
        }else if (this.stepsSinceTarget==this.parent.length){ //if the agent has no target
           this.currentSquare=this.parent.getSquare();

           if (!this.calculated){
                this.calculated=true;
                this.calculateOptimalPath();
            }

           this.calculateNextSquare(); //than we calculate a new one
           this.stepsSinceTarget=0; //as we just arrived on it
        }


        this.stepToSquare();
        this.stepsSinceTarget+=this.parent.speed;
    }


    
    @Override
    protected void calculateNextSquare(){
        if (this.parent.getParent().getLevel(this.parent.getLevel()).hasRightExit(this.currentSquare)){
            this.nextSquare=this.currentSquare+1;
            this.isExiting=true;
        } else if (this.parent.getParent().getLevel(this.parent.getLevel()).hasLeftExit(this.currentSquare)){
            this.nextSquare=this.currentSquare-1;
            this.isExiting=true;
        } else if (this.parent.getParent().getLevel(this.parent.getLevel()).hasBottomExit(this.currentSquare)){
            this.nextSquare=this.currentSquare+this.parent.size;
            this.isExiting=true;
        } else if (this.parent.getParent().getLevel(this.parent.getLevel()).hasTopExit(this.currentSquare)){
            this.nextSquare=this.currentSquare-this.parent.size;
            this.isExiting=true;
        } else {
            if (this.itinerary.isEmpty()){
                this.nextSquare=this.currentSquare;
                this.stuck=true;
            } else if (this.currentSquare==((Integer)this.itinerary.get(0)).intValue()){
                this.itinerary.remove(0);
                this.nextSquare=((Integer)this.itinerary.get(0)).intValue();
            }
            
        }
    }

    protected void calculateOptimalPath(){
        FloydWarshall fw= new FloydWarshall((UndirectedGraph)this.parent.getParent().getLevel(this.parent.getLevel()).getPathGraph());
        ArrayList fireExits=this.parent.getParent().getLevel(this.parent.getLevel()).getFireExits();

        int src=this.currentSquare;
        if(!fireExits.isEmpty()){
            int dst=((Integer)fireExits.get(0)).intValue();

            fw.getPath(src,dst);
            this.itinerary=fw.getItinerary();
            this.itinerary.add(0, (Integer)this.currentSquare);
        } else {
            this.itinerary=new ArrayList();
        }
    }

    public void calculateGraph(){
        if (!calculated)
            this.calculateOptimalPath();
    }
}
