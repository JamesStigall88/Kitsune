/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kitsune;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.Serializable;

/**
 *
 * @author Guillaume
 */
public class FollowerBehavior extends Behavior implements Serializable{

    private boolean calculated=false;

    private ArrayList itinerary;
    private FloydWarshall fw;
    
    public FollowerBehavior(Agent parent){
        this.type="follower";
        this.color=color.CYAN;
        this.parent=parent;

    }

    @Override
    public void step(){
        if (this.isExiting && this.stepsSinceTarget==this.parent.length){
            this.parent.exit();
        } else if (this.stepsSinceTarget==this.parent.length){ //if the agent has no target
           this.currentSquare=this.parent.getSquare();
           this.calculateOptimalPath();
           this.calculateNextSquare(); //calculate new square
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
                if (!this.itinerary.isEmpty())
                    this.nextSquare=((Integer)this.itinerary.get(0)).intValue();
            }
        }
    }


    protected void calculateOptimalPath(){
        if (!this.calculated){
            this.calculated=true;
            this.fw= new FloydWarshall((UndirectedGraph)this.parent.getParent().getLevel(this.parent.getLevel()).getPathGraph());
        }

        int src=this.currentSquare;
        int index=this.closestAgent();
        if (index!=-1){
            int dst=0;
            if (((Agent)this.parent.getParent().getAgents().get(index)).hasExited()){ //if the agent hasn't exited (if he has, then we can keep the same itinerary and be sure to reach the exit
            } else {
                dst=((Agent)this.parent.getParent().getAgents().get(index)).getSquare();            

                if (dst!=-1 && fw.getGraph().nodeExists(dst)){
                    fw.getPath(src,dst);
                    ArrayList tempItinerary=fw.getItinerary();
                    if (!tempItinerary.isEmpty()){
                        this.itinerary=tempItinerary;
                        this.itinerary.add(0,(Integer)this.currentSquare);
                    }
                } else if (!fw.getGraph().nodeExists(dst)){
                    this.itinerary=this.itinerary;
                } else {
                    this.itinerary= new ArrayList();
                }
            }
        
        } else {
            this.itinerary= new ArrayList();
        }
    }

    private int closestAgent(){
        ArrayList a= this.parent.getParent().getAgents();
        int index=-1;
        int i=-1;
        double minDistance=0.0;

        Iterator it=a.iterator();

        while (it.hasNext()){
            i+=1;
            Agent aTemp=(Agent)it.next();
            if (!aTemp.equals(this.parent)){
                double distanceTemp=getDistance(aTemp);
                if (distanceTemp<minDistance || minDistance==0){
                    index=i;
                }
            }
        }
        return index;
    }

    private double getDistance(Agent a){
        int aX=a.getX();
        int aY=a.getY();
        int x=this.parent.getX();
        int y=this.parent.getY();

        double distance= Math.sqrt(Math.pow((aX-x),2)+Math.pow((aY-y),2));

        return distance;
    }

}
