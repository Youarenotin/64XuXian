package com.xuxian.new_market.respository.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * Created by youarenotin on 2016/10/31.
 */
public class MigrationHelper {


    /**
     * 转移
     *
     * @param db
     * @param classes
     */
    public static void migrate(SQLiteDatabase db, Class<? extends AbstractDao<?, ?>>... classes) {
        generateTempTables(db, classes);
        createAllTables(db, false, classes);
        restoreData(db, classes);
    }

    /**
     * 创建临时表
     *
     * @param db
     * @param classes
     */
    private static void generateTempTables(SQLiteDatabase db, Class<? extends AbstractDao<?, ?>>[] classes) {
        for (int i = 0; i < classes.length; i++) {
            DaoConfig daoConfig = new DaoConfig(db, classes[i]);
            String tableName = daoConfig.tablename;
            if (!checkTable(db, tableName))
                continue;
            String tempTableName = tableName.concat("_TEMP");
            StringBuilder sb = new StringBuilder();
            sb.append("alter table ")
                    .append(tableName)
                    .append(" rename to '")
                    .append(tempTableName)
                    .append("'")
                    .append(";");
            db.execSQL(sb.toString());
        }

    }

    /**
     * 检查表是否存在
     *
     * @param db
     * @param tableName
     * @return
     */
    private static boolean checkTable(SQLiteDatabase db, String tableName) {
        Cursor cursor = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("select count(*) from ")
                    .append(" sqlite_master where ")
                    .append(" type = 'table' ")
                    .append(" and name = '")
                    .append(tableName)
                    .append("' ");
            cursor = db.rawQuery(sb.toString(), null);
            if (cursor.moveToNext() && cursor != null) {
                int value = cursor.getInt(0);
                if (value > 0)
                    return true;
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor = null;
            }
        }
        return false;
    }

    /**
     * 创建新表
     *
     * @param db
     * @param ifNotExits
     * @param classes
     */
    private static void createAllTables(SQLiteDatabase db, boolean ifNotExits, Class<? extends AbstractDao>[] classes) {
        reflectMethod(db, "createTable", ifNotExits, classes);
    }

    private static void reflectMethod(SQLiteDatabase db, String methodName, boolean ifNotExits, Class<? extends AbstractDao>[] classes) {
        if (classes.length < 1)
            return;

        for (Class clazz : classes) {
            try {
                Method method = clazz.getDeclaredMethod(methodName, SQLiteDatabase.class, boolean.class);
                method.invoke(null, db, ifNotExits);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将临时表数据写入新表 , 删除临时表
     *
     * @param db
     * @param classes
     */
    private static void restoreData(SQLiteDatabase db, Class<? extends AbstractDao<?, ?>>[] classes) {
        if (classes.length < 1)
            return;
        for (int i = 0; i < classes.length; i++) {
            DaoConfig daoConfig = new DaoConfig(db, classes[i]);
            String tablename = daoConfig.tablename;
            String tempTableName = tablename.concat("_TEMP");
            if (!checkTable(db, tempTableName))
                continue;
            List<String> columns = getColumns(db, tempTableName);
            columns = getColumns(db, tempTableName);
            List<String> propeties = new ArrayList<>(columns.size());
            for (int k = 0; k < daoConfig.properties.length; k++) {
                String property = daoConfig.properties[k].columnName;
                if (columns.contains(property)) {
                    propeties.add(property);
                }
            }
            if (propeties.size() > 0) {
                String columnsSql = TextUtils.join(",", propeties);
                StringBuilder sb = new StringBuilder();
                sb.append("insert into ")
                        .append(tablename)
                        .append(" (")
                        .append(columnsSql)
                        .append(" )")
                        .append(" select " + columnsSql)
                        .append(" from ")
                        .append(tempTableName)
                        .append(";");
                db.execSQL(sb.toString());
            }
            db.execSQL("drop table " + tempTableName);
        }
    }

    private static List<String> getColumns(SQLiteDatabase db, String tempTableName) {
        Cursor cursor = db.rawQuery("select * from " + tempTableName , null);
        try {
            if (cursor != null && cursor.getColumnCount() > 0) {
                return Arrays.asList(cursor.getColumnNames());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }

        }
        return new ArrayList<>();
    }
}
