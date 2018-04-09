package com.dwb.lottery_two.javabean;

import java.util.List;

/**
 * Created by dwb on 2018/3/27.
 */

public class HonePageListModel {
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

    public HonePageListModel.showapi_res_body getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(HonePageListModel.showapi_res_body showapi_res_body) {
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

        public List<HonePageListModel.showapi_res_body.result> getResult() {
            return result;
        }

        public void setResult(List<HonePageListModel.showapi_res_body.result> result) {
            this.result = result;
        }

        public class result{
            private String series;
            private String area;
            private String issuer;
            private String times;
            private String hots;
            private String high;
            private String code;
            private String notes;
            private String descr;

            public String getSeries() {
                return series;
            }

            public void setSeries(String series) {
                this.series = series;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getIssuer() {
                return issuer;
            }

            public void setIssuer(String issuer) {
                this.issuer = issuer;
            }

            public String getTimes() {
                return times;
            }

            public void setTimes(String times) {
                this.times = times;
            }

            public String getHots() {
                return hots;
            }

            public void setHots(String hots) {
                this.hots = hots;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getNotes() {
                return notes;
            }

            public void setNotes(String notes) {
                this.notes = notes;
            }

            public String getDescr() {
                return descr;
            }

            public void setDescr(String descr) {
                this.descr = descr;
            }
        }
    }
}
