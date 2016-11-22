package com.xuxian.new_market.listener.Monitor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by youarenotin on 2016/11/9.
 */

public class ShoppingCarMonitor {

    private static ShoppingCarMonitor instance;

    private Map<String , ICallback> maps=new HashMap<>();

    private ShoppingCarMonitor(){};

    public interface ICallback{
        void appOperation(MonitorEnum.ShoppingCarEnum eenum);
    }


    public static ShoppingCarMonitor getInstance() {
        if (instance == null) {
            synchronized (ShoppingCarMonitor.class) {
                if (instance == null) {
                    instance=new ShoppingCarMonitor();
                }
            }
        }
        return instance;
    }

    public void issueShoppingCarMonitor(MonitorEnum.ShoppingCarEnum mEnum) {
        if (maps.entrySet().size()==0)
            return;
        for (Map.Entry entry : maps.entrySet()){
            ((ShoppingCarMonitor.ICallback)entry.getValue()).appOperation(mEnum);
        }
    }

    public void registerShoppingCarMonitor(String name , ShoppingCarMonitor.ICallback callback){
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
