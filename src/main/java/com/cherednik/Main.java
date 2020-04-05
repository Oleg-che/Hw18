package com.cherednik;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;
import java.util.Objects;

public class Main {

    private static final String URL = "https://dl.dropboxusercontent.com/s/vxnydq4xjkmefrp/CLUVAL.java";

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        File file = new File("Text.txt");
        int second = getContentLength(client);
        writeTextToFile(getInfo(client, 0, second / 2), file);
        writeTextToFile(getInfo(client, (second / 2) + 1, getContentLength(client)), file);
    }

    private static void writeTextToFile(String text, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(text);
        } catch (IOException e) {
            System.out.println("File can`t found");
        }
    }

    private static String getInfo(OkHttpClient client, int first, int second) throws IOException {
        Request request = new Request.Builder()
                .url(URL)
                .method("GET", null)
                .addHeader("Range", "bytes=" + first + "-" + second)
                .build();
        return Objects.requireNonNull(client.newCall(request).execute().body()).string();
    }

    private static int getContentLength(OkHttpClient client) throws IOException {
        Request request = new Request.Builder()
                .url(URL)
                .head()
                .build();
        Response response = client.newCall(request).execute();
        return Integer.parseInt(Objects.requireNonNull(response.header("Content-Length")));
    }
}
