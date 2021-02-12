package com.global.mu.common;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpUtils {
    private static final String apiUrl = "https://www.globalmuonline.com";

    public static String callApi(String account, MethodEnum method, String endpoint, String cookie, Map<String, String> dataMap) throws IOException {
        System.out.println("Begin to " + endpoint + " for " + account);
        final String url = apiUrl + endpoint;
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        httpBuilder.readTimeout(60000, TimeUnit.MILLISECONDS);
        httpBuilder.callTimeout(60000, TimeUnit.MILLISECONDS);
        httpBuilder.writeTimeout(60000, TimeUnit.MILLISECONDS);
        httpBuilder.connectTimeout(60000, TimeUnit.MILLISECONDS);

        OkHttpClient httpClient = httpBuilder.build();
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        Request request;
        Response response = null;
        String result = "";

        try {

            switch (method) {
                case POST: {
                    FormBody.Builder formBuilder = new FormBody.Builder();

                    for (String key : dataMap.keySet())
                        formBuilder.add(key, dataMap.get(key));

                    RequestBody requestBody = formBuilder.build();
                    builder.addHeader("Cookie", cookie);
                    builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                    builder.addHeader("User-Agent", "Mozilla/5.0");
                    builder.addHeader("Accept", "text/html");
                    builder.addHeader("Connection", "keep-alive");
                    request = builder.post(requestBody).build();
                    break;
                }
                case GET: {
                    request = builder.get().build();
                    break;
                }
                default: {
                    throw new RuntimeException("Not support this method: " + method);
                }
            }

            response = httpClient.newCall(request).execute();

            if (response.isSuccessful())
                result = endpoint.equals(Constant.RESET) || endpoint.equals(Constant.GRAND_RESET)
                        ? response.body().string() : "success";
            else
                System.out.println("Response is not successful: Code " + response.code());
        } finally {
            if (response != null)
                response.close();

            httpClient.connectionPool().evictAll();
        }

        return result;
    }
}
