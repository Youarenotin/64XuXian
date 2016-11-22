package com.xuxian.new_market.Test.Geek;

/**
 * Created by youarenotin on 2016/11/18.
 */

public class Rect extends Shape{
    protected float height;

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    protected float width;

    public double getGirth(){
        return (float) (2*(height+width));
    }

    public double getArea(){
        return (float) (height*width);
    }

}
