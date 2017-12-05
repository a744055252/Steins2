package com.guanhuan.steins.biz;

/**
 * <基础业务类>
 *
 */
public interface Presenter<V> {
    void attachView(V view);

    void detachView(V view);

    String getName();

    /** activity被销毁时调用 */
    void onStop();
}
