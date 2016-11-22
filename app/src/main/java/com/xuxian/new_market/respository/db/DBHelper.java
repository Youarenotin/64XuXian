package com.xuxian.new_market.respository.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.market.new_xuxian.greendaodao.AddressDao;
import com.market.new_xuxian.greendaodao.DaoMaster;
import com.market.new_xuxian.greendaodao.DaoSession;
import com.market.new_xuxian.greendaodao.ShoppingCartGoodsDao;
import com.market.new_xuxian.greendaodao.StoreDao;
import com.market.new_xuxian.greendaodao.UserDao;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by youarenotin on 2016/10/31.
 */

public class DBHelper {
    private static DBHelper instance;

    /*------------------------------------------------*/
    private ShoppingCartGoodsDao shoppingCartGoodsDao;
    private AddressDao addressDao;
    private StoreDao storeDao;
    private UserDao userDao;
    /*------------------------------------------------*/

    private DBHelper(Context context) {
        init(context);
    }

    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (DBHelper.class) {
                if (instance == null) {
                    instance = new DBHelper(context);
                }
            }
        }
        return instance;
    }

    private void init(Context context) {
        HMROpenHelper helper = new HMROpenHelper(context,"xuxian.db",null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession session = daoMaster.newSession();
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
        addressDao=session.getAddressDao();
        storeDao=session.getStoreDao();
        shoppingCartGoodsDao=session.getShoppingCartGoodsDao();
        userDao=session.getUserDao();
    }

    public AddressDao getAddressDao() {
        return addressDao;
    }

    public ShoppingCartGoodsDao getShoppingCartGoodsDao() {
        return shoppingCartGoodsDao;
    }

    public StoreDao getStoreDao() {
        return storeDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
