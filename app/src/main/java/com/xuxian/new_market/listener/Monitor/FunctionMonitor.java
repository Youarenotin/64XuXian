package com.xuxian.new_market.listener.Monitor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by youarenotin on 2016/11/2.
 */

public class FunctionMonitor {

    private static  FunctionMonitor instance;

    public boolean showFunction=true;

    public boolean isSuspend=false;

    private Map<String,ICallback>  maps=new HashMap<String,ICallback>();

    public interface ICallback{
        void appOperation(MonitorEnum.FunctionEnum funcEnum);
    }

    private FunctionMonitor(){

    }

    public static FunctionMonitor getInstance() {
        if (instance == null) {
            synchronized (FunctionMonitor.class) {
                if (instance == null) {
                    instance = new FunctionMonitor();
                }
            }
        }
        return instance;
    }

    public void  issueFunctionMonitor(MonitorEnum.FunctionEnum arg){
        if (maps.keySet().size()==0)
            return ;
        if (isSuspend)
            return ;
        for (Map.Entry entry : maps.entrySet()){
            ((ICallback)entry.getValue()).appOperation(arg);
        }

    }

    public  void registerFuntionMonitorCallback(String className,ICallback callback){
        maps.put(className,callback);
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
