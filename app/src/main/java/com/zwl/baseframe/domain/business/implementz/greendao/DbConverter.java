package com.zwl.baseframe.domain.business.implementz.greendao;

import com.zwl.baseframe.domain.business.model.AlarmModel;
import com.zwl.baseframe.domain.business.model.WordModel;
import com.zwl.greendao.Alarm;
import com.zwl.greendao.Word;

/**
 * Created by hasee on 2017/7/30.
 */

public final class DbConverter {

    public static Word convertWordModel(WordModel wordModel) {
        Word word = new Word();
        if (wordModel.getId() != 0) {
            word.setId(wordModel.getId());
        }
        word.setName(wordModel.getName());
        word.setMeaning(wordModel.getMeaning());
        word.setPronunciation(wordModel.getPronunciation());
        word.setSaveTime(wordModel.getSaveTime());

        return word;
    }

    public static WordModel convertToWordModel(Word word) {
        return new WordModel(word.getId(), word.getName(), word.getMeaning(), word.getSaveTime());
    }

    public static Alarm convertAlarmModel(AlarmModel alarmModel) {
        Alarm alarm = new Alarm();
        if (alarm.getId() != 0) {
            alarm.setId(alarmModel.getId());
        }
        alarm.setHour(alarmModel.getHour());
        alarm.setMinute(alarmModel.getMinute());
        alarm.setOpen(alarmModel.isOpen());

        return alarm;
    }

    public static AlarmModel convertToAlarmModel(Alarm alarm) {
        return new AlarmModel(alarm.getId(), alarm.getHour(), alarm.getMinute(), alarm.getOpen());
    }
}
