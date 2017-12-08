package com.guanhuan.steins.biz;

import java.util.List;

/**
 * Created by guanhuan_li on 2017/12/8.
 */

public interface IAdapterView extends IMvpView {
    void setData(List<?> list);
}
