/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.guanhuan.steins.conn;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 *
 * @Auther: guanhuan_li
 * @Date: 16:39 2017/11/15
 */
public class SteinsRetrofit {

    final UserApi userService;

    private OkHttpClient client;

    private Interceptor authInterceptor;

    final static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .serializeNulls()
            .create();

    SteinsRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (SteinsFactory.isDebug()) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }
        httpClient.connectTimeout(12, TimeUnit.SECONDS);
        client = httpClient.build();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://192.168.8.113:8080/")
            .client(client)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create(gson));

        Retrofit steinsRest = builder.build();
        userService = steinsRest.create(UserApi.class);
    }

    public UserApi getUserService() {
        return userService;
    }

    public void setInterceptors(Interceptor interceptor){
        client.interceptors().add(interceptor);
    }

    public void removeInterceptors(Interceptor interceptor){
        client.interceptors().remove(interceptor);
    }

    public void setAuthInterceptor(Interceptor interceptor){
        this.authInterceptor = interceptor;
        this.setInterceptors(interceptor);
    }

    public void removeAuthInterceptor(){
        if(this.authInterceptor == null){
            throw new NullPointerException("AuthInterceptor is empty!");
        }
        this.removeInterceptors(this.authInterceptor);
    }
}
