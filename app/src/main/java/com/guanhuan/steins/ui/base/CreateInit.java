package com.guanhuan.steins.ui.base;

/**
 * <公共方法抽象>
 *
 */
public interface CreateInit {
    /**
     * 初始化布局组件
     */
    void initViews();

    /**
     * 增加按钮点击事件
     */
    void initListeners();

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 初始化公共头部
     */
    void setHeader();
}

