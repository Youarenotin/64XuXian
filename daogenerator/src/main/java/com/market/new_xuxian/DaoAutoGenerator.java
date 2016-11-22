package com.market.new_xuxian;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;
import sun.corba.EncapsInputStreamFactory;

public class DaoAutoGenerator {
    public static void main(String[] args) {
        int version = 3;
        String defaultPackage = "com.market.new_xuxian.greendao";
        // 正如你所见的，你创建了一个用于添加实体（Entity）的模式（Schema）对象。
        // 两个参数分别代表：数据库版本号与自动生成代码的包路径。
        Schema schema = new Schema(version, defaultPackage);
        //      当然，如果你愿意，你也可以分别指定生成的 Bean 与 DAO 类所在的目录，只要如下所示：
//      Schema schema = new Schema(1, "me.itangqi.bean");
//      schema.setDefaultJavaPackageDao("me.itangqi.dao");
        schema.setDefaultJavaPackageDao(defaultPackage + "dao");

        // 模式（Schema）同时也拥有两个默认的 flags，分别用来标示 entity 是否是 activie 以及是否使用 keep sections。
        // schema2.enableActiveEntitiesByDefault();
        // schema2.enableKeepSectionsByDefault();

        // 一旦你拥有了一个 Schema 对象后，你便可以使用它添加实体（Entities）了。
//        createTable(schema);
        create_ShoppingCartGoods_Table(schema);
        create_Store_Table(schema);
        create_User_Table(schema);
        create_Address_Table(schema);
        try {
            new DaoGenerator().generateAll(schema, "/Users/youarenotin/Documents/Android_Library/SSSSSSSSSSSSSSSSS/Respository/NewXuXian/app/src/main/java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Schema schema) {
        // 一个实体（类）就关联到数据库中的一张表，此处表名为「Note」（既类名）
        Entity entity = schema.addEntity("NewsChannelTable");
        // 你也可以重新给表命名
        // note.setTableName("NODE");

        // greenDAO 会自动根据实体类的属性值来创建表字段，并赋予默认值
        // 接下来你便可以设置表中的字段：
        // 与在 Java 中使用驼峰命名法不同，默认数据库中的命名是使用大写和下划线来分割单词的。
        // For example, a property called “creationDate” will become a database column “CREATION_DATE”.

        //        note.addIdProperty();
        /**
         * 频道名称
         */
        entity.addStringProperty("newsChannelName").notNull().primaryKey().index();
        /**
         * 频道id
         */
        entity.addStringProperty("newsChannelId").notNull();
        /**
         * 频道类型
         */
        entity.addStringProperty("newsChannelType").notNull();
        /**
         * 选中的频道
         */
        entity.addBooleanProperty("newsChannelSelect").notNull();
        /**
         * 频道的排序位置
         */
        entity.addIntProperty("newsChannelIndex").notNull();
        /**
         * 频道是否是固定的
         */
        entity.addBooleanProperty("newsChannelFixed");
    }

    static String shoppcar_table_name = "ShoppingCartGoods";
    static String Store_table_name = "Store";
    static String user_table_name="User";
    static String address_table_name="Address";

    private static void create_ShoppingCartGoods_Table(Schema schema) {
        Entity entity = schema.addEntity(shoppcar_table_name);
        entity.addStringProperty("cart_goods_nums");
        entity.addStringProperty("category_type");
        entity.addStringProperty("down_time");
        entity.addStringProperty("endtime");
        entity.addStringProperty("goods_type");
        entity.addStringProperty("icon");
        entity.addIntProperty("id");
        entity.addStringProperty("is_del");
        entity.addStringProperty("main_name");
        entity.addStringProperty("market_price");
        entity.addStringProperty("newimg");
        entity.addStringProperty("newprice");
        entity.addStringProperty("phonetips");
        entity.addStringProperty("price");
        entity.addStringProperty("real_name");
        entity.addStringProperty("selltype");
        entity.addStringProperty("show");
        entity.addStringProperty("soft_no");
        entity.addIntProperty("sold_num");
        entity.addStringProperty("starttime");
        entity.addIntProperty("store_num");
        entity.addIntProperty("store_nums");
        entity.addStringProperty("tipsimg");
        entity.addStringProperty("title");
        entity.addStringProperty("total_price_one");
        entity.addStringProperty("unit");
        entity.addStringProperty("up_time");
        entity.addStringProperty("userid");
        entity.addIntProperty("account");
        entity.addLongProperty("pk").primaryKey().autoincrement();
    }

    private static void create_Store_Table(Schema schema) {
        Entity entity = schema.addEntity(Store_table_name);
        entity.addLongProperty("id").primaryKey().autoincrement().notNull();
        entity.addStringProperty("area");
        entity.addStringProperty("area_id");
        entity.addStringProperty("bdate");
        entity.addStringProperty("bstarttime");
        entity.addStringProperty("city_area");
        entity.addStringProperty("city_id");
        entity.addStringProperty("city_name");
        entity.addStringProperty("distance");
        entity.addStringProperty("firsttime");
        entity.addDoubleProperty("lat");
        entity.addDoubleProperty("lng");
        entity.addStringProperty("message_alert");
        entity.addStringProperty("starttime");
        entity.addStringProperty("telphone");
        entity.addStringProperty("title");
        entity.addIntProperty("is_click");
        entity.addIntProperty("store_attr");
        entity.addIntProperty("store_status");
        entity.addIntProperty("type");
    }

    private static void create_User_Table(Schema schema) {
        Entity entity = schema.addEntity(user_table_name);
        entity.addStringProperty("userid").primaryKey();
        entity.addStringProperty("balance");
        entity.addStringProperty("birthday");
        entity.addStringProperty("email");
        entity.addStringProperty("group_name");
        entity.addStringProperty("head_ico");
        entity.addStringProperty("interest");
        entity.addStringProperty("occupation");
        entity.addStringProperty("pay_pwd");
        entity.addStringProperty("payment");
        entity.addStringProperty("phone");
        entity.addIntProperty("push_count");
        entity.addStringProperty("push_link");
        entity.addStringProperty("school");
        entity.addStringProperty("tocken");
        entity.addStringProperty("username");
        entity.addStringProperty("vip_endtime");
        entity.addIntProperty("developersid");
        entity.addIntProperty("exp");
        entity.addIntProperty("group_id");
        entity.addIntProperty("level");
        entity.addIntProperty("point");
        entity.addIntProperty("sex");
    }

    private static void create_Address_Table(Schema schema) {
        Entity entity = schema.addEntity(address_table_name);
        entity.addLongProperty("id").primaryKey().autoincrement();
        entity.addStringProperty("accept_name");
        entity.addStringProperty("area");
        entity.addStringProperty("area_id");
        entity.addStringProperty("city");
        entity.addStringProperty("city_id");
        entity.addStringProperty("d_address");
        entity.addStringProperty("is_default");
        entity.addStringProperty("l_address");
        entity.addStringProperty("mobile");
        entity.addIntProperty("sex");
        entity.addDoubleProperty("lat");
        entity.addDoubleProperty("lng");
    }
}
