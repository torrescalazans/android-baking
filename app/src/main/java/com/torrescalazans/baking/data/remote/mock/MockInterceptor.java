package com.torrescalazans.baking.data.remote.mock;
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

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MockInterceptor implements Interceptor {

    // Mock responses
    private final static String TEACHER_ID_1 = "[{\"id\":1,\"name\":\"Victor Apoyan\",\"ingredients\":[{\"quantity\": 2,\"measure\":\"CUP\",\"ingredient\": \"Graham Cracker crumbs\"},{\"quantity\":6,\"measure\":\"TBLSP\",\"ingredient\":\"unsalted butter, melted\"},\"steps\": [{\"id\": 0,\"shortDescription\":\"Recipe Introduction\",\"description\":\"Recipe Introduction\",\"videoURL\":\"https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4\",\"thumbnailURL\":\"\"},{\"id\":1,\"shortDescription\":\"Starting prep\",\"description\":\"1. Preheat the oven to 350\\u00b0F. Butter a 9\" deep dish pie pan.\",\"videoURL\":\"\",\"thumbnailURL\":\"\"},\"serving\":28,\"imageUrl\":\"test\"}]";

    @Override
    public Response intercept(Chain chain) throws IOException {
        String responseString = TEACHER_ID_1;
        Response response = null;

        response = new Response.Builder()
                .code(200)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .body(ResponseBody.create(MediaType.parse("application/json"),
                        responseString.getBytes()))
                .addHeader("content-type", "application/json")
                .build();

        return response;
    }
}
