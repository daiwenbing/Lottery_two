package com.dwb.lottery_two.javabean;

import java.util.List;

/**
 * Created by dwb on 2018/3/27.
 */

public class HonePageDetailListModel {
    private String showapi_res_code;
    private String showapi_res_error;
    private showapi_res_body showapi_res_body;

    public String getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(String showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public HonePageDetailListModel.showapi_res_body getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(HonePageDetailListModel.showapi_res_body showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public class showapi_res_body{
        private String ret_code;
        private List<result> result;

        public String getRet_code() {
            return ret_code;
        }

        public void setRet_code(String ret_code) {
            this.ret_code = ret_code;
        }

        public List<HonePageDetailListModel.showapi_res_body.result> getResult() {
            return result;
        }

        public void setResult(List<HonePageDetailListModel.showapi_res_body.result> result) {
            this.result = result;
        }

        public class result{
            private String timestamp;
            private String expect;
            private String time;
            private String name;
            private String code;
            private String openCode;

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }

            public String getExpect() {
                return expect;
            }

            public void setExpect(String expect) {
                this.expect = expect;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getOpenCode() {
                return openCode;
            }

            public void setOpenCode(String openCode) {
                this.openCode = openCode;
            }
        }
    }
}
