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

import retrofit2.http.Field;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 *
 * @Auther: guanhuan_li
 * @Date: 16:15 2017/11/15
 */
interface UserApi {
    @Headers({ "Content-Type: application/json" })
    @POST("User/token")
    Observable<String> getToken(@Field("account") String account,
                                @Field("password") String password);
}
