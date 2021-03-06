package com.market.new_xuxian.greendaodao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.market.new_xuxian.greendao.Address;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table ADDRESS.
*/
public class AddressDao extends AbstractDao<Address, Long> {

    public static final String TABLENAME = "ADDRESS";

    /**
     * Properties of entity Address.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "ID");
        public final static Property Accept_name = new Property(1, String.class, "accept_name", false, "ACCEPT_NAME");
        public final static Property Area = new Property(2, String.class, "area", false, "AREA");
        public final static Property Area_id = new Property(3, String.class, "area_id", false, "AREA_ID");
        public final static Property City = new Property(4, String.class, "city", false, "CITY");
        public final static Property City_id = new Property(5, String.class, "city_id", false, "CITY_ID");
        public final static Property D_address = new Property(6, String.class, "d_address", false, "D_ADDRESS");
        public final static Property Is_default = new Property(7, String.class, "is_default", false, "IS_DEFAULT");
        public final static Property L_address = new Property(8, String.class, "l_address", false, "L_ADDRESS");
        public final static Property Mobile = new Property(9, String.class, "mobile", false, "MOBILE");
        public final static Property Sex = new Property(10, Integer.class, "sex", false, "SEX");
        public final static Property Lat = new Property(11, Double.class, "lat", false, "LAT");
        public final static Property Lng = new Property(12, Double.class, "lng", false, "LNG");
    };


    public AddressDao(DaoConfig config) {
        super(config);
    }
    
    public AddressDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'ADDRESS' (" + //
                "'ID' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'ACCEPT_NAME' TEXT," + // 1: accept_name
                "'AREA' TEXT," + // 2: area
                "'AREA_ID' TEXT," + // 3: area_id
                "'CITY' TEXT," + // 4: city
                "'CITY_ID' TEXT," + // 5: city_id
                "'D_ADDRESS' TEXT," + // 6: d_address
                "'IS_DEFAULT' TEXT," + // 7: is_default
                "'L_ADDRESS' TEXT," + // 8: l_address
                "'MOBILE' TEXT," + // 9: mobile
                "'SEX' INTEGER," + // 10: sex
                "'LAT' REAL," + // 11: lat
                "'LNG' REAL);"); // 12: lng
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'ADDRESS'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Address entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String accept_name = entity.getAccept_name();
        if (accept_name != null) {
            stmt.bindString(2, accept_name);
        }
 
        String area = entity.getArea();
        if (area != null) {
            stmt.bindString(3, area);
        }
 
        String area_id = entity.getArea_id();
        if (area_id != null) {
            stmt.bindString(4, area_id);
        }
 
        String city = entity.getCity();
        if (city != null) {
            stmt.bindString(5, city);
        }
 
        String city_id = entity.getCity_id();
        if (city_id != null) {
            stmt.bindString(6, city_id);
        }
 
        String d_address = entity.getD_address();
        if (d_address != null) {
            stmt.bindString(7, d_address);
        }
 
        String is_default = entity.getIs_default();
        if (is_default != null) {
            stmt.bindString(8, is_default);
        }
 
        String l_address = entity.getL_address();
        if (l_address != null) {
            stmt.bindString(9, l_address);
        }
 
        String mobile = entity.getMobile();
        if (mobile != null) {
            stmt.bindString(10, mobile);
        }
 
        Integer sex = entity.getSex();
        if (sex != null) {
            stmt.bindLong(11, sex);
        }
 
        Double lat = entity.getLat();
        if (lat != null) {
            stmt.bindDouble(12, lat);
        }
 
        Double lng = entity.getLng();
        if (lng != null) {
            stmt.bindDouble(13, lng);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Address readEntity(Cursor cursor, int offset) {
        Address entity = new Address( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // accept_name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // area
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // area_id
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // city
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // city_id
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // d_address
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // is_default
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // l_address
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // mobile
            cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10), // sex
            cursor.isNull(offset + 11) ? null : cursor.getDouble(offset + 11), // lat
            cursor.isNull(offset + 12) ? null : cursor.getDouble(offset + 12) // lng
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Address entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAccept_name(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setArea(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setArea_id(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCity(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCity_id(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setD_address(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setIs_default(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setL_address(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setMobile(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setSex(cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10));
        entity.setLat(cursor.isNull(offset + 11) ? null : cursor.getDouble(offset + 11));
        entity.setLng(cursor.isNull(offset + 12) ? null : cursor.getDouble(offset + 12));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Address entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Address entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
