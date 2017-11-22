package com.guanhuan.steins.http;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * 个人的host校验
 * Created by guanhuan_li on 2017/11/22.
 */

public class MyHostNameVerifier implements HostnameVerifier {
    @Override
    public boolean verify(String hostname, SSLSession session) {
        return true;
    }
}
