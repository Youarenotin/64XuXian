package com.xuxian.new_market.respository.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.market.new_xuxian.greendaodao.AddressDao;
import com.market.new_xuxian.greendaodao.DaoMaster;
import com.market.new_xuxian.greendaodao.ShoppingCartGoodsDao;
import com.market.new_xuxian.greendaodao.StoreDao;
import com.market.new_xuxian.greendaodao.UserDao;

/**
 *
 * Created by youarenotin on 2016/10/31.
 */
public class HMROpenHelper extends DaoMaster.OpenHelper {

    /**
     * 可升级数据库的greendao openhelper
     * @param context
     * @param name
     * @param factory
     */
    public HMROpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //自定义数据库升级
        //1.根据现有表重命名创建临时表
        //2.创建新表
        //3.将临时表数据写到新表中
        //4.删除临时表
        MigrationHelper.migrate(db, StoreDao.class, UserDao.class, ShoppingCartGoodsDao.class, AddressDao.class);
    }
}
