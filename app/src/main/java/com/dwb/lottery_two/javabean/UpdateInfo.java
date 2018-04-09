package com.dwb.lottery_two.javabean;

/**
 * 版本检测
 * Created by dell on 2017/5/12.
 */

public class UpdateInfo {
    private String errorCode;//错误编码
    private String errorMsg;//错误信息
    private data data;
    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorMsg() {
        return errorMsg;
    }
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    public data getData() {
        return data;
    }
    public void setData(data data) {
        this.data = data;
    }

    public static class data{
        private String version;     //版本号
        private String description; //版本信息
        private String url;  		//apk下载路径
        private String app;
        private String addTime;
        public String getApp() {
            return app;
        }
        public void setApp(String app) {
            this.app = app;
        }
        public String getAddTime() {
            return addTime;
        }
        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getVersion() {
            return version;
        }
        public void setVersion(String version) {
            this.version = version;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public String getUrl() {
            return url;
        }
        public void setUrl(String url) {
            this.url = url;
        }
    }


}
