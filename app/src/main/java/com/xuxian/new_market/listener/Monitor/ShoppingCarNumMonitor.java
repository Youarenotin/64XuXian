package com.xuxian.new_market.listener.Monitor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by youarenotin on 2016/11/2.
 */

public class ShoppingCarNumMonitor {

    private static ShoppingCarNumMonitor instance;

    private Map<String,ICallback> maps=new HashMap<>();

    public interface ICallback{
        void appOperation(MonitorEnum.ShoppingCarNumEnum mEnum,int num, boolean isIncrease);
    }

    private ShoppingCarNumMonitor(){}

    public static ShoppingCarNumMonitor getInstance() {
        if (instance == null) {
            synchronized (ShoppingCarNumMonitor.class) {
                if (instance == null) {
                    instance=new ShoppingCarNumMonitor();
                }
            }
        }
        return instance;
    }

    public void issueShoppingCarNumMonitor(MonitorEnum.ShoppingCarNumEnum mEnum,int num, boolean isIncrease) {
        if (maps.entrySet().size()==0)
            return;
        for (Map.Entry entry : maps.entrySet()){
            ((ICallback)entry.getValue()).appOperation(mEnum,num,isIncrease);
        }
    }

    public void registerShoppingCarNumMonitor(String name , ICallback callback){
        maps.put(name,callback);
    }

    public boolean isRegister(String key) {
        if (maps.get(key) != null) {
            return true;
        }
        return false;
    }

    public void cancelRegister(String key) {
        maps.remove(key);
    }

    public void cancelAllRegisters(){
        this.maps.clear();
    }
}
