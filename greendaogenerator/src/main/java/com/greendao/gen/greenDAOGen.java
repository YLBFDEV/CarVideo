package com.greendao.gen;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class greenDAOGen {
    /**
     * 生成代码包名
     */
    public static final String PACKAGE_NAME = "com.gjjx.carvideo.db";
    /**
     * 版本号
     */
    public static final int SCHEMA_VERSION = 1;
    /**
     * <p> /表示根目录</p>
     * <p> ./表示当前路径</p>
     * <p> ../表示上一级父目录<p>
     */
    public static final String OUT_DIR = "./app/src/main/java-gen";
    /**
     * 实体表名
     */
    public static final String TB_SUBJECT = "TBSubject";
    /**
     * 实体表名
     */
    public static final String TB_COURSE = "TBCourse";
    /**
     * 实体表名
     */
    public static final String TB_CLASS = "TBClass";

    public static void main(String[] args) {
        // 正如你所见的，你创建了一个用于添加实体（Entity）的模式（Schema）对象。
        Schema schema = new Schema(SCHEMA_VERSION, PACKAGE_NAME);
        //当然，如果你愿意，你也可以分别指定生成的 Bean 与 DAO 类所在的目录，只要如下所示：
        //Schema schema = new Schema(1, "me.itangqi.bean");
        //schema.setDefaultJavaPackageDao("me.itangqi.dao");

        // 模式（Schema）同时也拥有两个默认的 flags，分别用来标示 entity 是否是 activie 以及是否使用 keep sections。
        // schema2.enableActiveEntitiesByDefault();
        // schema2.enableKeepSectionsByDefault();

        // 一旦你拥有了一个 Schema 对象后，你便可以使用它添加实体（Entities）了。
        addEntities(schema);

        // 最后我们将使用 DAOGenerator 类的 generateAll() 方法自动生成代码，此处你需要根据自己的情况更改输出目录（既之前创建的 java-gen)。
        // 其实，输出目录的路径可以在 build.gradle 中设置，有兴趣的朋友可以自行搜索，这里就不再详解。
        try {
            new DaoGenerator().generateAll(schema, OUT_DIR);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成失败");
        }
    }

    /**
     * 创建实体关系
     *
     * @param schema
     */
    private static void addEntities(Schema schema) {

        // 一个实体（类）就关联到数据库中的一张表，此处表名为「TB_SUBJECT」（既类名）
        Entity subject_entity = schema.addEntity(TB_SUBJECT);
        // 你也可以重新给表命名
        // subject_entity.setTableName("TBSUBJECT");

        // greenDAO 会自动根据实体类的属性值来创建表字段，并赋予默认值
        // 接下来你便可以设置表中的字段：
        subject_entity.addIdProperty().autoincrement().getProperty();
        subject_entity.addStringProperty("name");
        subject_entity.addStringProperty("summary");

        Entity course_entity = schema.addEntity(TB_COURSE);
        course_entity.addIdProperty().autoincrement().getProperty();
        //创建外键关系
        Property subject_fkProperty = course_entity.addLongProperty("sub_id").notNull().getProperty();
        course_entity.addToOne(subject_entity, subject_fkProperty);
        course_entity.addStringProperty("name");
        course_entity.addStringProperty("summary");
        course_entity.addStringProperty("url");

        Entity class_entity = schema.addEntity(TB_CLASS);
        class_entity.addIdProperty().autoincrement().getProperty();
        // 与在 Java 中使用驼峰命名法不同，默认数据库中的命名是使用大写和下划线来分割单词的。
        // For example, a property called “creationDate” will become a database column “CREATION_DATE”.
        Property fkProperty = class_entity.addLongProperty("c_id").notNull().getProperty();
        class_entity.addToOne(course_entity, fkProperty);
        class_entity.addStringProperty("name");
        class_entity.addStringProperty("summary");
        class_entity.addStringProperty("filename");
    }

}

