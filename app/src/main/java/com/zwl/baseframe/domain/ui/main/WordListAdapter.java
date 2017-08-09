package com.zwl.baseframe.domain.ui.main;

import android.app.Activity;

import com.zwl.base_lib.view.recyclerview.BaseRecyclerViewAdapter;
import com.zwl.baseframe.R;
import com.zwl.baseframe.domain.business.model.WordModel;

import java.util.List;

/**
 * Created by hasee on 2017/8/6.
 */

public class WordListAdapter extends BaseRecyclerViewAdapter<WordModel> {
    public WordListAdapter(Activity baseActivity, int layoutResId, List<WordModel> wordModels) {
        super(baseActivity, layoutResId, wordModels);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<WordModel> holder, int position) {
        WordModel wordModel = getItemByPostion(position);
        holder.setText(R.id.tv_word, wordModel.getName());
        holder.setText(R.id.tv_meaning, wordModel.getMeaning());
        holder.setText(R.id.tv_left_time, getLeftTime(wordModel.getSaveTime()));
    }

    private String getLeftTime(long saveTime) {
        long dif = System.currentTimeMillis() + 5 * 24 * 60 * 60 * 1000 - saveTime;
        int day = (int) (dif / (24 * 60 * 60 * 1000));
        int hour = (int) ((dif % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000));
        return baseActivity.getString(R.string.word_main_left_time, day, hour);
    }
}
