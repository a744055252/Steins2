package com.guanhuan.steins.data.entity;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;

import java.io.Serializable;

/**
 * Created by 74405 on 2017/11/15.
 */

public class ACMsg implements Serializable{

    @PrimaryKey(PrimaryKey.AssignType.AUTO_INCREMENT)
    @Column("id")
    public long id;

    @Column("terraceId")
    public String terraceId;

    @Column("imageUrl")
    public String imageUrl;

    @Column("auther")
    public String auther;

    @Column("autherId")
    public String autherId;

    @Column("createTime")
    public long createTime;

    @Column("spiderTime")
    public long spiderTime;

    @Column("title")
    public String title;

    @Column("click")
    public long click;

    @Column("review")
    public long review;

    @Column("type")
    public String type;

    @Column("acUrl")
    public String acUrl;

    @Column("description")
    public String description;
}
