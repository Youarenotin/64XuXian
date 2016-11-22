package com.market.new_xuxian.greendaodao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.market.new_xuxian.greendao.User;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table USER.
*/
public class UserDao extends AbstractDao<User, String> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Userid = new Property(0, String.class, "userid", true, "USERID");
        public final static Property Balance = new Property(1, String.class, "balance", false, "BALANCE");
        public final static Property Birthday = new Property(2, String.class, "birthday", false, "BIRTHDAY");
        public final static Property Email = new Property(3, String.class, "email", false, "EMAIL");
        public final static Property Group_name = new Property(4, String.class, "group_name", false, "GROUP_NAME");
        public final static Property Head_ico = new Property(5, String.class, "head_ico", false, "HEAD_ICO");
        public final static Property Interest = new Property(6, String.class, "interest", false, "INTEREST");
        public final static Property Occupation = new Property(7, String.class, "occupation", false, "OCCUPATION");
        public final static Property Pay_pwd = new Property(8, String.class, "pay_pwd", false, "PAY_PWD");
        public final static Property Payment = new Property(9, String.class, "payment", false, "PAYMENT");
        public final static Property Phone = new Property(10, String.class, "phone", false, "PHONE");
        public final static Property Push_count = new Property(11, Integer.class, "push_count", false, "PUSH_COUNT");
        public final static Property Push_link = new Property(12, String.class, "push_link", false, "PUSH_LINK");
        public final static Property School = new Property(13, String.class, "school", false, "SCHOOL");
        public final static Property Tocken = new Property(14, String.class, "tocken", false, "TOCKEN");
        public final static Property Username = new Property(15, String.class, "username", false, "USERNAME");
        public final static Property Vip_endtime = new Property(16, String.class, "vip_endtime", false, "VIP_ENDTIME");
        public final static Property Developersid = new Property(17, Integer.class, "developersid", false, "DEVELOPERSID");
        public final static Property Exp = new Property(18, Integer.class, "exp", false, "EXP");
        public final static Property Group_id = new Property(19, Integer.class, "group_id", false, "GROUP_ID");
        public final static Property Level = new Property(20, Integer.class, "level", false, "LEVEL");
        public final static Property Point = new Property(21, Integer.class, "point", false, "POINT");
        public final static Property Sex = new Property(22, Integer.class, "sex", false, "SEX");
    };


    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'USER' (" + //
                "'USERID' TEXT PRIMARY KEY NOT NULL ," + // 0: userid
                "'BALANCE' TEXT," + // 1: balance
                "'BIRTHDAY' TEXT," + // 2: birthday
                "'EMAIL' TEXT," + // 3: email
                "'GROUP_NAME' TEXT," + // 4: group_name
                "'HEAD_ICO' TEXT," + // 5: head_ico
                "'INTEREST' TEXT," + // 6: interest
                "'OCCUPATION' TEXT," + // 7: occupation
                "'PAY_PWD' TEXT," + // 8: pay_pwd
                "'PAYMENT' TEXT," + // 9: payment
                "'PHONE' TEXT," + // 10: phone
                "'PUSH_COUNT' INTEGER," + // 11: push_count
                "'PUSH_LINK' TEXT," + // 12: push_link
                "'SCHOOL' TEXT," + // 13: school
                "'TOCKEN' TEXT," + // 14: tocken
                "'USERNAME' TEXT," + // 15: username
                "'VIP_ENDTIME' TEXT," + // 16: vip_endtime
                "'DEVELOPERSID' INTEGER," + // 17: developersid
                "'EXP' INTEGER," + // 18: exp
                "'GROUP_ID' INTEGER," + // 19: group_id
                "'LEVEL' INTEGER," + // 20: level
                "'POINT' INTEGER," + // 21: point
                "'SEX' INTEGER);"); // 22: sex
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'USER'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
 
        String userid = entity.getUserid();
        if (userid != null) {
            stmt.bindString(1, userid);
        }
 
        String balance = entity.getBalance();
        if (balance != null) {
            stmt.bindString(2, balance);
        }
 
        String birthday = entity.getBirthday();
        if (birthday != null) {
            stmt.bindString(3, birthday);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(4, email);
        }
 
        String group_name = entity.getGroup_name();
        if (group_name != null) {
            stmt.bindString(5, group_name);
        }
 
        String head_ico = entity.getHead_ico();
        if (head_ico != null) {
            stmt.bindString(6, head_ico);
        }
 
        String interest = entity.getInterest();
        if (interest != null) {
            stmt.bindString(7, interest);
        }
 
        String occupation = entity.getOccupation();
        if (occupation != null) {
            stmt.bindString(8, occupation);
        }
 
        String pay_pwd = entity.getPay_pwd();
        if (pay_pwd != null) {
            stmt.bindString(9, pay_pwd);
        }
 
        String payment = entity.getPayment();
        if (payment != null) {
            stmt.bindString(10, payment);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(11, phone);
        }
 
        Integer push_count = entity.getPush_count();
        if (push_count != null) {
            stmt.bindLong(12, push_count);
        }
 
        String push_link = entity.getPush_link();
        if (push_link != null) {
            stmt.bindString(13, push_link);
        }
 
        String school = entity.getSchool();
        if (school != null) {
            stmt.bindString(14, school);
        }
 
        String tocken = entity.getTocken();
        if (tocken != null) {
            stmt.bindString(15, tocken);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(16, username);
        }
 
        String vip_endtime = entity.getVip_endtime();
        if (vip_endtime != null) {
            stmt.bindString(17, vip_endtime);
        }
 
        Integer developersid = entity.getDevelopersid();
        if (developersid != null) {
            stmt.bindLong(18, developersid);
        }
 
        Integer exp = entity.getExp();
        if (exp != null) {
            stmt.bindLong(19, exp);
        }
 
        Integer group_id = entity.getGroup_id();
        if (group_id != null) {
            stmt.bindLong(20, group_id);
        }
 
        Integer level = entity.getLevel();
        if (level != null) {
            stmt.bindLong(21, level);
        }
 
        Integer point = entity.getPoint();
        if (point != null) {
            stmt.bindLong(22, point);
        }
 
        Integer sex = entity.getSex();
        if (sex != null) {
            stmt.bindLong(23, sex);
        }
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // userid
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // balance
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // birthday
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // email
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // group_name
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // head_ico
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // interest
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // occupation
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // pay_pwd
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // payment
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // phone
            cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11), // push_count
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // push_link
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // school
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // tocken
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // username
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // vip_endtime
            cursor.isNull(offset + 17) ? null : cursor.getInt(offset + 17), // developersid
            cursor.isNull(offset + 18) ? null : cursor.getInt(offset + 18), // exp
            cursor.isNull(offset + 19) ? null : cursor.getInt(offset + 19), // group_id
            cursor.isNull(offset + 20) ? null : cursor.getInt(offset + 20), // level
            cursor.isNull(offset + 21) ? null : cursor.getInt(offset + 21), // point
            cursor.isNull(offset + 22) ? null : cursor.getInt(offset + 22) // sex
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setUserid(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setBalance(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setBirthday(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setEmail(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setGroup_name(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setHead_ico(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setInterest(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setOccupation(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setPay_pwd(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setPayment(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setPhone(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setPush_count(cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11));
        entity.setPush_link(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setSchool(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setTocken(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setUsername(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setVip_endtime(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setDevelopersid(cursor.isNull(offset + 17) ? null : cursor.getInt(offset + 17));
        entity.setExp(cursor.isNull(offset + 18) ? null : cursor.getInt(offset + 18));
        entity.setGroup_id(cursor.isNull(offset + 19) ? null : cursor.getInt(offset + 19));
        entity.setLevel(cursor.isNull(offset + 20) ? null : cursor.getInt(offset + 20));
        entity.setPoint(cursor.isNull(offset + 21) ? null : cursor.getInt(offset + 21));
        entity.setSex(cursor.isNull(offset + 22) ? null : cursor.getInt(offset + 22));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(User entity, long rowId) {
        return entity.getUserid();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(User entity) {
        if(entity != null) {
            return entity.getUserid();
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
