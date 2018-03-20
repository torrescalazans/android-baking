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

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

@AutoValue
public abstract class Ingredient implements Parcelable {

    @NonNull
    public abstract int quantity();

    @NonNull
    public abstract String measure();

    @NonNull
    public abstract String name();

    public static Ingredient create(int quantity, String measure, String name) {
        return new AutoValue_Ingredient(quantity, measure, name);
    }

    public static final class ListTypeAdapter implements com.ryanharter.auto.value.parcel.TypeAdapter<List<Ingredient>> {
        @Override
        public List<Ingredient> fromParcel(Parcel in) {
            return in.createTypedArrayList(new Creator<Ingredient>() {
                @Override
                public Ingredient createFromParcel(Parcel source) {
                    return AutoValue_Ingredient.CREATOR.createFromParcel(source);
                }

                @Override
                public Ingredient[] newArray(int size) {
                    return new Ingredient[size];
                }
            });
        }

        @Override
        public void toParcel(List<Ingredient> value, Parcel dest) {
            dest.writeTypedList(value);
        }
    }

    public static TypeAdapter<Ingredient> typeAdapter(Gson gson) {
        return new AutoValue_Ingredient.GsonTypeAdapter(gson);
    }
}
