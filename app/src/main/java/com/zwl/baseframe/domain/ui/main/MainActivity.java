package com.zwl.baseframe.domain.ui.main;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.zwl.base_lib.view.ViewUtils;
import com.zwl.baseframe.BaseApplication;
import com.zwl.baseframe.R;
import com.zwl.baseframe.domain.business.model.WordModel;
import com.zwl.baseframe.domain.ui.base.BaseActivity;
import com.zwl.baseframe.domain.ui.settting.SettingActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.sfsu.cs.orange.ocr.CaptureActivity;

public class MainActivity extends BaseActivity
        implements MainContract.IMainView {

    private static final String TAG = MainActivity.class.getSimpleName();

    SearchView searchView;

    @BindView(R.id.rv_words)
    RecyclerView rvWords;

    WordListAdapter wordListAdapter;

    @Inject
    MainContract.IMainPresenter iMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);
        ButterKnife.bind(this);

        iMainPresenter.setView(this);

        try {
            initView();
        } catch (Exception e) {
            e.printStackTrace();
        }

        iMainPresenter.start();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));//设置主标题
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initRv();
    }

    private void initRv() {
        ViewUtils.addHorizonDecorationToRecyclerView(rvWords, getResources().getColor(R.color.gray), 1);
        rvWords.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void initSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.v(TAG, query);
                iMainPresenter.searchWord(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    protected void injectActivity() {
        BaseApplication.getInstance().getActivityComponent(this).inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        initSearchView();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SettingActivity.start(this);
            return true;
        }

        if (id == R.id.action_scan) {
            CaptureActivity.start(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showSearchResult(WordModel wordModel) {
        showToast(wordModel.getMeaning());
    }

    @Override
    public void showSavedWords(final List<WordModel> wordModels) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                wordListAdapter = new WordListAdapter(MainActivity.this, R.layout.item_word, wordModels);
                rvWords.setAdapter(wordListAdapter);
            }
        });
    }

    @Override
    public void notifyWordListChange() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (wordListAdapter != null) {
                    wordListAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void clearSearch() {
        searchView.setIconified(true);
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
        } else {
            super.onBackPressed();
        }
    }
}
