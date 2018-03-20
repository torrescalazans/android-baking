package com.torrescalazans.baking.ui.main;

import com.torrescalazans.baking.data.model.Recipe;
import com.torrescalazans.baking.data.model.Ribot;
import com.torrescalazans.baking.ui.base.MvpView;

import java.util.List;

public interface MainMvpView extends MvpView {

    void showRibots(List<Ribot> ribots);

    void showRecipes(List<Recipe> recipes);

    void showRibotsEmpty();

    void showRecipesEmpty();

    void showError();

}
