package com.cherednik;

import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

public class Post {

    private static final String URL = "https://postman-echo.com/post";
    private static final String FORMAT = "application/x-www-form-urlencoded";

    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        MediaType mediaType = MediaType.parse(FORMAT);
        RequestBody requestBody = RequestBody.create("foo1=bar1&foo2=bar2", mediaType);
        Request request = new Request.Builder()
                .url(URL)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(Objects.requireNonNull(response.body()).string());

    }
}
