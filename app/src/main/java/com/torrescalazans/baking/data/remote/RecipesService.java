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

package com.torrescalazans.baking.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.torrescalazans.baking.data.model.Recipe;
import com.torrescalazans.baking.data.remote.mock.MockInterceptor;
import com.torrescalazans.baking.util.MyGsonTypeAdapterFactory;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface RecipesService {

    String ENDPOINT = "https://api.ribot.io/";

    @GET("recipes/baking.json")
    Observable<List<Recipe>> getRecipes();

    final static OkHttpClient client = new OkHttpClient
            .Builder()
            .addInterceptor(new MockInterceptor())
            .build();

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static RecipesService newRecipesService() {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(MyGsonTypeAdapterFactory.create())
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();

            return retrofit.create(RecipesService.class);
        }
    }
}
