package com.guanhuan.steins.constant;

 /**
 * <事件类型>
 *
 */
public enum Event {
     /** 图片成功 */
     IMAGE_LOADER_SUCCESS("图片加载成功"),

     /** 用户登陆成功 */
     USER_LOGING_SUCCESS("用户登陆成功"),

     /** 成功获取Token,获取用户信息 */
     USER_TOKEN_SUCCESS("成功获取Token"),

     /** 成功获取acfun 香蕉榜 */
     ACFUN_BANANA_CUCCESS("成功获取acfun香蕉榜");

     private String msg;

     Event(String msg){
         this.msg = msg;
     }

     public String getMsg() {
         return msg;
     }
 }
