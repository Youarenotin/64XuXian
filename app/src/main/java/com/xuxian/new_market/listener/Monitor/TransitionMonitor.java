package com.xuxian.new_market.listener.Monitor;

import com.ab.util.AbPreferenceUtils;
import com.xuxian.new_market.App;
import com.xuxian.new_market.common.ApiConstant;
import com.xuxian.new_market.listener.Monitor.MonitorEnum.TransitionEnum;

import java.util.HashMap;
import java.util.Map;

import static com.xuxian.new_market.listener.Monitor.MonitorEnum.TransitionEnum.GRID_LAYOUT;
import static com.xuxian.new_market.listener.Monitor.MonitorEnum.TransitionEnum.LIST_LAYOUT;

/**
 * Created by youarenotin on 2016/11/6.
 */

public class TransitionMonitor {

    private static TransitionMonitor instance;

    private HashMap<String, ICallback> maps = new HashMap<>();

    public   TransitionEnum mLayout = LIST_LAYOUT;

    private TransitionMonitor() {
        String value = AbPreferenceUtils.loadPrefString(App.getAppContext(), ApiConstant.goodslayout, "0");
        if (value.equals("0")){
            mLayout=LIST_LAYOUT;
        }
        else {
            mLayout=GRID_LAYOUT;
        }
    }

    public interface ICallback {
        void appOperation(TransitionEnum mEnum);
    }

    public static TransitionMonitor getInstance() {
        if (instance == null) {
            synchronized (TransitionMonitor.class) {
                if (instance == null) {
                    instance = new TransitionMonitor();
                }
            }
        }
        return instance;
    }

    public void issueTransitionMonitor(TransitionEnum mEnum) {
        if (maps.entrySet().size() == 0)
            return;

            if (mLayout == LIST_LAYOUT){
                for (Map.Entry entry : maps.entrySet()) {
                    ((ICallback) entry.getValue()).appOperation(GRID_LAYOUT);
                }
                mLayout=GRID_LAYOUT;
                AbPreferenceUtils.savePrefString(App.getAppContext(),ApiConstant.goodslayout,"1");
            }
            else {
                for (Map.Entry entry : maps.entrySet()) {
                    ((ICallback) entry.getValue()).appOperation(LIST_LAYOUT);
                }
                mLayout=LIST_LAYOUT;
                AbPreferenceUtils.savePrefString(App.getAppContext(),ApiConstant.goodslayout,"0");
            }
//        if (mEnum == GRID_LAYOUT)
//            mLayout = GRID_LAYOUT;
//        else {
//            mLayout = LIST_LAYOUT;
//        }
    }

    public void registerTransitionMonitor(String name, ICallback callback) {
        maps.put(name, callback);
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

    public void cancelAllRegisters() {
        this.maps.clear();
    }
}
