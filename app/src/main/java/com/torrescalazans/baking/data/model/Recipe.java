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

package com.torrescalazans.baking.data.model;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.parcel.ParcelAdapter;

import java.util.List;

@AutoValue
public abstract class Recipe implements Comparable<Recipe>, Parcelable {

    @NonNull
    @SerializedName("id")
    public abstract int id();

    @NonNull
    @SerializedName("name")
    public abstract String name();

    @NonNull
    @ParcelAdapter(Ingredient.ListTypeAdapter.class)
    @SerializedName("ingredients")
    public abstract List<Ingredient> ingredients();

    @NonNull
    @ParcelAdapter(Step.ListTypeAdapter.class)
    @SerializedName("steps")
    public abstract List<Step> steps();

    @NonNull
    @SerializedName("servings")
    public abstract int servings();

    @Nullable
    @SerializedName("image")
    public abstract String imageUrl();

    public static Recipe.Builder builder() {
        return new AutoValue_Recipe.Builder();
    }

    public static Recipe create(Recipe recipe) {
        return new AutoValue_Recipe(recipe.id(), recipe.name(), recipe.ingredients(),
                recipe.steps(), recipe.servings(), recipe.imageUrl());
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Recipe.Builder setId(int id);
        public abstract Recipe.Builder setName(String name);
        public abstract Recipe.Builder setIngredients(List<Ingredient> ingredients);
        public abstract Recipe.Builder setSteps(List<Step> steps);
        public abstract Recipe.Builder setServings(int servings);
        public abstract Recipe.Builder setImageUrl(String imageUrl);
        public abstract Recipe build();
    }

    @Override
    public int compareTo(@NonNull Recipe anotherRecipe) {
        return anotherRecipe.name().compareToIgnoreCase(anotherRecipe.name());
    }

    public static TypeAdapter<Recipe> typeAdapter(Gson gson) {
        return new AutoValue_Recipe.GsonTypeAdapter(gson);
    }
}
