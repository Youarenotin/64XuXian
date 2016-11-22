package com.xuxian.new_market.Test;


import com.xuxian.new_market.Test.Geek.Circle;
import com.xuxian.new_market.Test.Geek.Rect;
import com.xuxian.new_market.Test.Geek.Shape;
import com.xuxian.new_market.Test.Geek.Square;

/**
 * Created by youarenotin on 2016/10/21.
 */
public class Test {
    public static void main(String[] args) {
        Shape[] mShape = new Shape[3];
        Circle circle = new Circle(); circle.setRadius(10.0f);
        Rect rect = new Rect(); rect.setHeight(5.5f);rect.setWidth(134.5f);
        Square square = new Square(); square.setWidth(8.8f);

//        for (int i =0 ; i<arrays.length ; i++) {
//            System.out.println("array["+i+"]---面积:"+arrays[i].getArea());
//            System.out.println("array["+i+"]---周长:"+arrays[i].getGirth());
//        }
    }
}
