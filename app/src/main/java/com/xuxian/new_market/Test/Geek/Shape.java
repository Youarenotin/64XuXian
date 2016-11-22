package com.xuxian.new_market.Test.Geek;

/**
 * Created by youarenotin on 2016/11/18.
 */

public abstract class Shape {
    protected Shape[] mShapeArray=new Shape[5];

    public abstract double getGirth();

    public abstract double getArea();

    public void add(Shape s){
        for (int i = 0 ; i<mShapeArray.length;i++) {
            if (mShapeArray[i] == null) {
                mShapeArray[i]=s;
                break;
            }
        }
    }
}
