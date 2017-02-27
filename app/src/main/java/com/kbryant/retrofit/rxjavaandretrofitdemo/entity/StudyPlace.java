package com.kbryant.retrofit.rxjavaandretrofitdemo.entity;


import java.util.List;

/**
 * 区域和日期
 * Created by WX on 2017/2/3 0003.
 */

public class StudyPlace {

    /**
     * areaName : 合川区
     * teachPlaces : [{"areaName":"合川区","teachPlace":"合川教学站","code":"100005"}]
     */

    private String areaName;
    private List<TeachPlacesBean> teachPlaces;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List<TeachPlacesBean> getTeachPlaces() {
        return teachPlaces;
    }

    public void setTeachPlaces(List<TeachPlacesBean> teachPlaces) {
        this.teachPlaces = teachPlaces;
    }

    public static class TeachPlacesBean {
        /**
         * areaName : 合川区
         * teachPlace : 合川教学站
         * code : 100005
         */

        private String areaName;
        private String teachPlace;
        private String code;

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getTeachPlace() {
            return teachPlace;
        }

        public void setTeachPlace(String teachPlace) {
            this.teachPlace = teachPlace;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
