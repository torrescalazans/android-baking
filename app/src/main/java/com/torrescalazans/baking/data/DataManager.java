package com.torrescalazans.baking.data;

import com.torrescalazans.baking.data.local.DatabaseHelper;
import com.torrescalazans.baking.data.local.PreferencesHelper;
import com.torrescalazans.baking.data.model.Recipe;
import com.torrescalazans.baking.data.model.Ribot;
import com.torrescalazans.baking.data.remote.RecipesService;
import com.torrescalazans.baking.data.remote.RibotsService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

@Singleton
public class DataManager {

    private final RibotsService mRibotsService;
    private final RecipesService mRecipesService;

    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public DataManager(RibotsService ribotsService,
                       RecipesService recipesService,
                       PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper) {
        mRibotsService = ribotsService;
        mRecipesService = recipesService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public Observable<Ribot> syncRibots() {
        return mRibotsService.getRibots()
                .concatMap(new Function<List<Ribot>, ObservableSource<? extends Ribot>>() {
                    @Override
                    public ObservableSource<? extends Ribot> apply(@NonNull List<Ribot> ribots)
                            throws Exception {
                        return mDatabaseHelper.setRibots(ribots);
                    }
                });
    }

    public Observable<List<Ribot>> getRibots() {
        return mDatabaseHelper.getRibots().distinct();
    }

    public Observable<Recipe> syncRecipes() {
        return mRecipesService.getRecipes()
                .concatMap(new Function<List<Recipe>, ObservableSource<? extends Recipe>>() {

                    @Override
                    public ObservableSource<? extends Recipe> apply(@NonNull List<Recipe> recipes)
                            throws Exception {
                        return mDatabaseHelper.setRecipes(recipes);
                    }
                });
    }

    public Observable<List<Recipe>> getRecipes() {
        return mDatabaseHelper.getRecipes().distinct();
    }

}
