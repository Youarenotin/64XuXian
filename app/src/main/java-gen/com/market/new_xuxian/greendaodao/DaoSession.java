package com.market.new_xuxian.greendaodao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.market.new_xuxian.greendao.ShoppingCartGoods;
import com.market.new_xuxian.greendao.Store;
import com.market.new_xuxian.greendao.User;
import com.market.new_xuxian.greendao.Address;

import com.market.new_xuxian.greendaodao.ShoppingCartGoodsDao;
import com.market.new_xuxian.greendaodao.StoreDao;
import com.market.new_xuxian.greendaodao.UserDao;
import com.market.new_xuxian.greendaodao.AddressDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig shoppingCartGoodsDaoConfig;
    private final DaoConfig storeDaoConfig;
    private final DaoConfig userDaoConfig;
    private final DaoConfig addressDaoConfig;

    private final ShoppingCartGoodsDao shoppingCartGoodsDao;
    private final StoreDao storeDao;
    private final UserDao userDao;
    private final AddressDao addressDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        shoppingCartGoodsDaoConfig = daoConfigMap.get(ShoppingCartGoodsDao.class).clone();
        shoppingCartGoodsDaoConfig.initIdentityScope(type);

        storeDaoConfig = daoConfigMap.get(StoreDao.class).clone();
        storeDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        addressDaoConfig = daoConfigMap.get(AddressDao.class).clone();
        addressDaoConfig.initIdentityScope(type);

        shoppingCartGoodsDao = new ShoppingCartGoodsDao(shoppingCartGoodsDaoConfig, this);
        storeDao = new StoreDao(storeDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);
        addressDao = new AddressDao(addressDaoConfig, this);

        registerDao(ShoppingCartGoods.class, shoppingCartGoodsDao);
        registerDao(Store.class, storeDao);
        registerDao(User.class, userDao);
        registerDao(Address.class, addressDao);
    }
    
    public void clear() {
        shoppingCartGoodsDaoConfig.getIdentityScope().clear();
        storeDaoConfig.getIdentityScope().clear();
        userDaoConfig.getIdentityScope().clear();
        addressDaoConfig.getIdentityScope().clear();
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

    public AddressDao getAddressDao() {
        return addressDao;
    }

}