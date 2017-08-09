package com.zwl.greendao;


import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class WordDaoGentator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.zwl.greendao");

        //下载
        addWordTable(schema);
        addAlarmTable(schema);
        new DaoGenerator().generateAll(schema, "..\\app\\src\\main\\java-gen");
    }

    private static void addWordTable(Schema schema) {
        Entity word = schema.addEntity("Word");
        word.addIdProperty().autoincrement();
        word.addStringProperty("name");
        word.addStringProperty("pronunciation");
        word.addStringProperty("meaning");
        word.addLongProperty("saveTime");
    }

    private static void addAlarmTable(Schema schema) {
        Entity alarm = schema.addEntity("Alarm");
        alarm.addIdProperty().autoincrement();
        alarm.addIntProperty("hour");
        alarm.addIntProperty("minute");
        alarm.addBooleanProperty("open");
    }

}
