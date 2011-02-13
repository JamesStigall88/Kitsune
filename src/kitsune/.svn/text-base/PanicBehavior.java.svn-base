package kitsune;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.Serializable;

/**
 *
 * @author Guillaume
 */
public class PanicBehavior extends Behavior implements Serializable{
    private boolean[] visitedSquares;


    public PanicBehavior(Agent parent){
        this.type="panic";
        this.color=color.MAGENTA;
        this.parent=parent;
        this.stepsSinceTarget=this.parent.length;
        
        this.visitedSquares= new boolean[this.parent.size*this.parent.size];
    }

    @Override
    public void step(){
        if (this.isExiting && this.stepsSinceTarget==this.parent.length){
            this.parent.exit();
        }else if (this.stepsSinceTarget==this.parent.length){ //if the agent has no target
           this.currentSquare=this.parent.getSquare();
           this.calculateNextSquare(); //than we calculate a new one
           visitedSquares[this.currentSquare]=true; //we do so
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
            ArrayList toDelete= new ArrayList();//this will contain all the destinations to remove from the list of possibilities
            //we build an array with all the 8 possible destinations
            ArrayList a= new ArrayList();

            //we only add the cells that are accessible
            if (!this.parent.getParent().getLevel(this.parent.getLevel()).hasRightWall(this.currentSquare))
                a.add((Integer)this.currentSquare+1); //right

            if (!this.parent.getParent().getLevel(this.parent.getLevel()).hasLeftWall(this.currentSquare))
                a.add(this.currentSquare-1); //left

            if (!this.parent.getParent().getLevel(this.parent.getLevel()).hasBottomWall(this.currentSquare))
                a.add(this.currentSquare+this.parent.size); //down

            if (!this.parent.getParent().getLevel(this.parent.getLevel()).hasTopWall(this.currentSquare))
                a.add(this.currentSquare-this.parent.size); //up

            //we mark those that have been visited to remove
            Iterator it= a.iterator();
            while (it.hasNext()){
                int i=((Integer)it.next()).intValue();
                if (this.visitedSquares[i]){ //if we visited square i
                    toDelete.add((Integer)i); //then it is not part of possibilites anymore
                }
            }

            //we remove all that need to be
            it = toDelete.iterator();
            while (it.hasNext()){
                Integer target=(Integer)it.next();
                a.remove(target);
            }

           //we pick one at random to go to
            if (a.size()!=0){
                int randIndex= (int)(Math.random()*10)%a.size();
                this.nextSquare= ((Integer)a.get(randIndex)).intValue();
            } else { //if there are no possibilities we stay where we are
                this.nextSquare=this.currentSquare;
                this.stuck=true;
            }
        }
    }
}
