package kitsune;

import java.io.Serializable;

/**
 * @author Guillaume
 */
public class Label implements Serializable{
    private int x;
    private int y;
    private String label;

    public Label(int x, int y, String label){
        this.x=x;
        this.y=y;
        this.label=label;
    }

    public String getLabel(){
        return this.label;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

}
