package com.example.crudmvvm.clients;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static <T> T client(Class<T> service, String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(
                        GsonConverterFactory.create(
                                new GsonBuilder().setLenient().create()
                        )
                )
                .build();

        return retrofit.create(service);
    }
}
