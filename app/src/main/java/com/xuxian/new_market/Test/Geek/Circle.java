package com.xuxian.new_market.Test.Geek;

/**
 * Created by youarenotin on 2016/11/18.
 */

public class Circle extends Shape{
    private float radius;

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public double getGirth(){
        return (float) (Math.PI*2*radius);
    }

    public double getArea(){
        return (float) (Math.PI*radius*radius);
    }
}
