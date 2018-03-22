package com.torrescalazans.baking.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.torrescalazans.baking.R;
import com.torrescalazans.baking.data.SyncService;
import com.torrescalazans.baking.data.model.Recipe;
import com.torrescalazans.baking.data.model.Ribot;
import com.torrescalazans.baking.ui.base.BaseActivity;
import com.torrescalazans.baking.util.DialogFactory;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements MainMvpView {

    private static final String EXTRA_TRIGGER_SYNC_FLAG =
            "com.torrescalazans.baking.ui.main.MainActivity.EXTRA_TRIGGER_SYNC_FLAG";

    @Inject MainPresenter mMainPresenter;
    @Inject RibotsAdapter mRibotsAdapter;
    @Inject RecipeAdapter mRecipeAdapter;

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    /**
     * Return an Intent to start this Activity.
     * triggerDataSyncOnCreate allows disabling the background sync service onCreate. Should
     * only be set to false during testing.
     */
    public static Intent getStartIntent(Context context, boolean triggerDataSyncOnCreate) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_TRIGGER_SYNC_FLAG, triggerDataSyncOnCreate);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //mRecyclerView.setAdapter(mRibotsAdapter);
        mRecyclerView.setAdapter(mRecipeAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMainPresenter.attachView(this);
        //mMainPresenter.loadRibots();

        mMainPresenter.loadRecipes();

        if (getIntent().getBooleanExtra(EXTRA_TRIGGER_SYNC_FLAG, true)) {
            startService(SyncService.getStartIntent(this));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mMainPresenter.detachView();
    }

    /***** MVP View methods implementation *****/

    @Override
    public void showRibots(List<Ribot> ribots) {
        mRibotsAdapter.setRibots(ribots);
        mRibotsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showRecipes(List<Recipe> recipes) {
        mRecipeAdapter.setRecipes(recipes);
        mRecipeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_ribots))
                .show();
    }

    @Override
    public void showRibotsEmpty() {
        mRibotsAdapter.setRibots(Collections.<Ribot>emptyList());
        mRibotsAdapter.notifyDataSetChanged();
        Toast.makeText(this, R.string.empty_ribots, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showRecipesEmpty() {
        mRecipeAdapter.setRecipes(Collections.<Recipe>emptyList());
        mRecipeAdapter.notifyDataSetChanged();

        Toast.makeText(this, R.string.empty_recipes, Toast.LENGTH_LONG).show();
    }
}
