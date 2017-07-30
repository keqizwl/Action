package com.zwl.greendao;


import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class WordDaoGentator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.zwl.greendao");

        //下载
        addWordTable(schema);
        addAlarm(schema);
        new DaoGenerator().generateAll(schema, "..\\app\\src\\main\\java-gen");
    }

    private static void addWordTable(Schema schema) {
        Entity note = schema.addEntity("Word");
        note.addIdProperty().primaryKey().autoincrement();
        note.addStringProperty("hour");
        note.addStringProperty("pronunciation");
        note.addStringProperty("meaning");
        note.addLongProperty("saveTime");
    }

    private static void addAlarm(Schema schema) {
        Entity note = schema.addEntity("Alarm");
        note.addLongProperty("id").primaryKey();
        note.addStringProperty("hour");
        note.addStringProperty("minute");
        note.addLongProperty("open");
    }

}
