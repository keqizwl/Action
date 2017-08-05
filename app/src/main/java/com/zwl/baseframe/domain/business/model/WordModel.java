package com.zwl.baseframe.domain.business.model;

/**
 * Created by hasee on 2017/6/26.
 */

public class WordModel {
    private long id;
    private String name;
    private String pronunciation;
    private String meaning;
    private long saveTime;


    public WordModel(long id, String name, String meaning, long saveTime) {
        this.id = id;
        this.name = name;
        this.meaning = meaning;
        this.saveTime = saveTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public String getMeaning() {
        return meaning;
    }

    public long getSaveTime() {
        return saveTime;
    }

    @Override
    public String toString() {
        return "WordModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pronunciation='" + pronunciation + '\'' +
                ", meaning='" + meaning + '\'' +
                ", saveTime=" + saveTime +
                '}';
    }
}
