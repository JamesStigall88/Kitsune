package kitsune;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class represents the building in its entirety
 * @author Guillaume
 */
public class Environment implements Serializable {

    public Environment(){
        this.levels= new ArrayList();
        this.agents= new ArrayList();
    }

    //////////////////////////////////////////////////////////////////////////
    /* Getters */
    //////////////////////////////////////////////////////////////////////////
    public int getNumLevels(){
        return this.levels.size();
    }

    public int getNumAgents(){ //returns the number of agents in the environment
        return this.agents.size();
    }

    public int getNumExits(){
        int numExits=0;
        Iterator it = this.levels.iterator();

        while (it.hasNext()){
            Level l = (Level)it.next();
            numExits+=l.getNumExits();
        }

        return numExits;
    }

    public int getNumDoors(){
        int numDoors=0;
        Iterator it = this.levels.iterator();

        while (it.hasNext()){
            Level l = (Level)it.next();
            numDoors+=l.getNumDoors();
        }

        return numDoors;
    }


    public Level getLevel(int i){
        return (Level)levels.get(i);
    }
    public ArrayList getAgents(){
        return this.agents;
    }

    //////////////////////////////////////////////////////////////////////////

    public void addLevel(){
        Level l = new Level();
        this.levels.add(l);
    }
    public void removeLevel(int i){
        this.levels.remove(i);
    }
    public void clearLevels(){
        this.levels.clear();
    }

    public void addAgent(int x, int y, int l, String s){
        Agent a = new Agent(x,y,l,s,this);
        this.agents.add(a);
    }
    public void removeAgent(int x, int y, int l){ //searches for agent in the vicinity of x,y on level l and removes it if there's one

        Iterator it= this.agents.iterator();
        boolean erased=false;
        int i=-1;

        while (it.hasNext() && !erased){
            i+=1;
            Agent a=(Agent)it.next();
            
            if ((Math.abs(a.getX()-x)<12) && (Math.abs(a.getY()-y)<12) && a.getLevel()==l){
                erased=true;
            }
        }

        if(erased)
            this.agents.remove(i);

        
    }
    public void clearAgents(){
        this.agents.clear();
    }

    public long getFirstAgentExit(){
        long time=0;

        Iterator it= this.agents.iterator();

        while (it.hasNext()){
            Agent a= (Agent)it.next();
            
            if (time==0)
                time= a.getExitTime();

            if (a.hasExited() && a.getExitTime()<time)
                time=a.getExitTime();
        }

        return time;
    }

    public long getLastAgentExit(){
        long time=0;

        Iterator it= this.agents.iterator();

        while (it.hasNext()){
            Agent a= (Agent)it.next();

            if (time==0)
                time= a.getExitTime();

            if (a.hasExited() && a.getExitTime()>time)
                time=a.getExitTime();
        }

        return time;
    }

    public int getTotalAgentsExited(){
        int sum=0;
        Iterator it= this.agents.iterator();

        while (it.hasNext()){
            Agent a= (Agent)it.next();
            if (a.hasExited())
                sum+=1;
        }
       return sum;        
    }

    public int getTotalAgentsStuck(){
        int sum=0;
        Iterator it= this.agents.iterator();

        while (it.hasNext()){
            Agent a= (Agent)it.next();
            if (a.isStuck())
                sum+=1;
        }
       return sum;
    }

    public int getTotalAgentsExiting(){
        return this.agents.size()-this.getTotalAgentsExited()-this.getTotalAgentsStuck();
    }

    public double getSuccessPercentage(){
        return ((double)this.getTotalAgentsExited()/(double)this.agents.size())*100;
    }

    public double getAverageExitTime(){
        double totalTime=0.0;
        int totalAgents=0;

        Iterator it= this.agents.iterator();

        while (it.hasNext()){
            Agent a= (Agent)it.next();

            if (a.hasExited()){
                totalAgents+=1;
                totalTime+=a.getExitTime();
            }
        }

        if (totalAgents==0)
            return 0;
        return totalTime/(double)totalAgents;
    }

    public long getSimTime(){
        return this.simRunTime;
    }

    public void setSimRunTime(long i){
        this.simRunTime=i;
    }
    //////////////////////////////////////////////////////////////////////////
    /*Variables*/
    //////////////////////////////////////////////////////////////////////////

    private ArrayList levels;
    private ArrayList agents;

    public long simRunTime=0;

}
