/*
 * Copyright (C) 2018 Jose Torres
 *
 * This file is part of android-baking.
 *
 * android-baking is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2
 * as published by the Free Software Foundation.
 *
 * android-baking is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package com.torrescalazans.baking.ui.main;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.torrescalazans.baking.R;
import com.torrescalazans.baking.data.model.Recipe;
import com.torrescalazans.baking.data.model.Ribot;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    public Context mContext;
    private List<Recipe> mRecipes;

    @Inject
    public RecipeAdapter() {
        mRecipes = new ArrayList<>();
    }

    public void setRecipes(List<Recipe> recipes) {
        mRecipes = recipes;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item, parent, false);

        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecipeViewHolder holder, final int position) {
        Recipe recipe = mRecipes.get(position);

        holder.recipeNameTextView.setText(recipe.name());
        holder.recipeStepsTextView.setText(mContext.getResources().getString(
                R.string.recipe_steps, recipe.steps().size()));

        holder.recipeItemCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Recipe recipe = mRecipes.get(position);
                Timber.d( "Recipe CardView Clicked: " + recipe);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cv_recipe_item) CardView recipeItemCardView;
        @BindView(R.id.tv_recipe_name) TextView recipeNameTextView;
        @BindView(R.id.tv_recipe_steps) TextView recipeStepsTextView;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }
    }
}
