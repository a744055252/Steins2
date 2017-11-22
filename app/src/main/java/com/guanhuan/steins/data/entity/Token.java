package com.guanhuan.steins.data.entity;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;

/**
 * Created by guanhuan_li on 2017/11/21.
 */

@Table("token")
public class Token {

    @PrimaryKey(PrimaryKey.AssignType.AUTO_INCREMENT)
    private int id;


    private String account;

    private String token;
}
