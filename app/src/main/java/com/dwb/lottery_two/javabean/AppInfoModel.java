package com.dwb.lottery_two.javabean;

/**
 * Created by dwb on 2018/3/31.
 */

public class AppInfoModel {
    /*	"id": "815",
	"0": "815",
	"url": "http://bxvip.oss-cn-zhangjiakou.aliyuncs.com/xycai/xycaizy.apk",
	"1": "http://bxvip.oss-cn-zhangjiakou.aliyuncs.com/xycai/xycaizy.apk",
	"type": "android",
	"2": "android",
	"show_url": "0",
	"3": "0",
	"appid": "lottery.dwb.com.lottery",
	"4": "lottery.dwb.com.lottery",
	"comment": "? ????",
	"5": "? ????",
	"request_num": "0",
	"6": "0",
	"createAt": "2018-03-31 19:51:01",
	"7": "2018-03-31 19:51:01",
	"updateAt": "2018-03-31 19:51:01",
	"8": "2018-03-31 19:51:01"*/
    private String id;
    private String url;
    private String type;
    private String show_url;
    private String appid;
    private String comment;
    private String request_num;
    private String createAt;
    private String updateAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShow_url() {
        return show_url;
    }

    public void setShow_url(String show_url) {
        this.show_url = show_url;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRequest_num() {
        return request_num;
    }

    public void setRequest_num(String request_num) {
        this.request_num = request_num;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

}
