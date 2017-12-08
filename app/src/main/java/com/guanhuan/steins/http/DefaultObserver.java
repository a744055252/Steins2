package com.guanhuan.steins.http;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.JsonParseException;
import com.guanhuan.steins.R;
import com.guanhuan.steins.config.Constants;
import com.guanhuan.steins.bean.model.ResultModel;
import com.guanhuan.steins.util.Toasts;

import org.json.JSONException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;
import retrofit2.HttpException;
import rx.Observer;

import static com.guanhuan.steins.http.DefaultObserver.ExceptionReason.*;


public abstract class DefaultObserver<T extends ResultModel> implements Observer<T> {

    private static final String TAG = "DefaultObserver";

    //  Activity 是否在执行onStop()时取消订阅
    private boolean isAddInStop = false;

    public DefaultObserver(){
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onNext(T response) {
        Log.i(TAG, "onNext:"+ response.toString());
        if (!response.isError()) {
            onSuccess(response);
        } else {
            onFail(response);
        }
    }


    @Override
    public void onError(Throwable e) {
        Log.e(TAG, e.getMessage(), e);
        if (e instanceof HttpException) {     //   HTTP错误
            onException(ExceptionReason.BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误
            onException(CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onException(CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误
            onException(PARSE_ERROR);
        } else {
            onException(UNKNOWN_ERROR);
        }
    }

    /**
     * 请求成功
     *
     * @param response 服务器返回的数据
     */
    abstract public void onSuccess(T response);

    /**
     * 服务器返回数据，但响应码不为200
     *
     * @param response 服务器返回的数据
     */
    private void onFail(T response) {
        String message = response.getMessage();
        Log.i(TAG, "onFail: "+ response.getCode() + "Message:" + response.getMessage());
        if (TextUtils.isEmpty(message)) {
            Toasts.showShort(response.getCode()+" : "+Constants.ERROR_MSG);
        } else {
            Toasts.showShort(response.getCode()+" : "+message);
        }
    }

    /**
     * 请求异常
     *
     * @param reason
     */
    private void onException(ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
                Toasts.showShort(R.string.connect_error);
                break;
            case CONNECT_TIMEOUT:
                Toasts.showShort(R.string.connect_timeout);
                break;
            case BAD_NETWORK:
                Toasts.showShort(R.string.bad_network);
                break;
            case PARSE_ERROR:
                Toasts.showShort(R.string.parse_error);
                break;
            case UNKNOWN_ERROR:
            default:
                Toasts.showShort(R.string.unknown_error);
                break;
        }
    }

    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }
}
