package com.guanhuan.steins.bean.entity;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by 74405 on 2017/11/15.
 */

@Table("user")
public class User implements Serializable{

    @PrimaryKey(PrimaryKey.AssignType.AUTO_INCREMENT)
    @Column("userId")
    public long userId;

    @Column("userName")
    public String userName;

    @Column("account")
    public String account;

    @Column("password")
    public String password;

    @Column("createTime")
    public String createTime;

    @Column("sex")
    public String sex;

    @Column("age")
    public int age;

    @Column("email")
    public String email;

    @Column("phone")
    public String phone;

    @Column("token")
    public String token;

    @Override
    public String toString() {
        return "[userId:"+userId+", userName:"+userName+", account:"+account+", password:"+password+
                ", createTime:"+createTime+", sex:"+sex+", age:"+age+", email:"+email+", phone:"+phone+
                ", token:"+token+"]";
    }
}
