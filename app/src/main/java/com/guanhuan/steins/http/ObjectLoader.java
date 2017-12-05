package com.guanhuan.steins.http;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.JsonParseException;
import com.guanhuan.steins.R;
import com.guanhuan.steins.bean.entity.User;
import com.guanhuan.steins.bean.model.ResultModel;
import com.guanhuan.steins.config.Constants;
import com.guanhuan.steins.util.Toasts;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.lang.reflect.ParameterizedType;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.HttpException;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.guanhuan.steins.http.DefaultObserver.ExceptionReason.CONNECT_ERROR;
import static com.guanhuan.steins.http.DefaultObserver.ExceptionReason.CONNECT_TIMEOUT;
import static com.guanhuan.steins.http.DefaultObserver.ExceptionReason.PARSE_ERROR;
import static com.guanhuan.steins.http.DefaultObserver.ExceptionReason.UNKNOWN_ERROR;

/**
 *
 * 将一些重复的操作提出来，放到父类以免Loader 里每个接口都有重复代码
 * Created by guanhuan_li
 *
 */

public class ObjectLoader<T extends ResultModel> {

    private static final String TAG = "ObjectLoader";

    /**
     *
     * @param observable
     * @return
     */
    protected  Observable<T> observe(Observable<T> observable){
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
