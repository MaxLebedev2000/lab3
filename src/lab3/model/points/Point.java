package lab3.model.points;

import java.io.Serializable;

public class Point implements Serializable {

    private float x;
    private String y;

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    private float r;

    public float getX(){
        return x;
    }

    public void setX(float x){
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
