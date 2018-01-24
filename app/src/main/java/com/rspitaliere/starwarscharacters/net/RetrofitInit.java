package com.rspitaliere.starwarscharacters.net;

import android.content.Context;

import java.net.CookieManager;
import java.util.concurrent.TimeUnit;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rspitaliere on 14/07/17.
 */

public class RetrofitInit {

    private static Retrofit mRestAdapter;

    public static Retrofit getRetrofit(){

        if (mRestAdapter == null) {

            OkHttpClient client;

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(7, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .cookieJar(new JavaNetCookieJar(new CookieManager()))
                    .build();


            mRestAdapter = new Retrofit.Builder()
                    .baseUrl("https://swapi.co/api/people/")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        return mRestAdapter;
    }
}
