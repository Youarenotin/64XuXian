package com.xuxian.new_market;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ab.util.AbPreferenceUtils;
import com.market.new_xuxian.greendaodao.DaoMaster;
import com.market.new_xuxian.greendaodao.DaoSession;
import com.xuxian.new_market.common.ApiConstant;
import com.xuxian.new_market.respository.db.HMROpenHelper;

/**
 * Created by youarenotin on 2016/10/21.
 */

public class App extends Application {
    public static Context getAppContext() {
        return mAppContext;
    }

    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;

        if (AbPreferenceUtils.loadPrefString(this, ApiConstant.STORE_ID) == null)
            AbPreferenceUtils.savePrefString(this, ApiConstant.STORE_ID, "882");
        if (AbPreferenceUtils.loadPrefString(this, ApiConstant.USER_ID) == null)
            AbPreferenceUtils.savePrefString(this, ApiConstant.USER_ID, "1609759");
        if (AbPreferenceUtils.loadPrefString(this, ApiConstant.USER_ID) == null)
            AbPreferenceUtils.savePrefString(this, ApiConstant.USER_ID, "1609759");
        if (AbPreferenceUtils.loadPrefString(this, ApiConstant.USER_TOKEN) == null) {
            AbPreferenceUtils.savePrefString(this,ApiConstant.USER_TOKEN,"3474ef67bc63be27bfa24cf91ce20984");
        }
        if (AbPreferenceUtils.loadPrefString(this, ApiConstant.USER_PHONE) == null) {
            AbPreferenceUtils.savePrefString(this,ApiConstant.USER_PHONE,"18401205020");
        }
        if (AbPreferenceUtils.loadPrefString(this, ApiConstant.store_address) == null) {
            AbPreferenceUtils.savePrefString(this,ApiConstant.store_address,"昌平西关三角地权金城对面康洁洗衣店");
        }
        if (AbPreferenceUtils.loadPrefString(this,ApiConstant.CITY_ID)==null){
            AbPreferenceUtils.savePrefString(this,ApiConstant.CITY_ID,"110000");
        }
    }

    /**
     * 初始化
     */
    private void initDB() {
        HMROpenHelper helper = new HMROpenHelper(this, "xuxian.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession session = daoMaster.newSession();
    }

}
