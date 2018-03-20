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
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class Step implements Parcelable {

    @NonNull
    @SerializedName("id")
    public abstract int id();

    @NonNull
    @SerializedName("shortDescription")
    public abstract String shortDescription();

    @NonNull
    @SerializedName("description")
    public abstract String description();

    @Nullable
    @SerializedName("videoURL")
    public abstract String videoUrl();

    @Nullable
    @SerializedName("thumbnailURL")
    public abstract String thumbnailUrl();

    public static Step create(int id, String shortDescription, String description,
                              String videoUrl, String thumbnailUrl) {

        return new AutoValue_Step(id, shortDescription, description, videoUrl, thumbnailUrl);
    }

    public static final class ListTypeAdapter implements com.ryanharter.auto.value.parcel.TypeAdapter<List<Step>> {
        @Override
        public List<Step> fromParcel(Parcel in) {
            return in.createTypedArrayList(new Creator<Step>() {
                @Override
                public Step createFromParcel(Parcel source) {
                    return AutoValue_Step.CREATOR.createFromParcel(source);
                }

                @Override
                public Step[] newArray(int size) {
                    return new Step[size];
                }
            });
        }

        @Override
        public void toParcel(List<Step> value, Parcel dest) {
            dest.writeTypedList(value);
        }
    }

    public static TypeAdapter<Step> typeAdapter(Gson gson) {
        return new AutoValue_Step.GsonTypeAdapter(gson);
    }
}
