package com.jx.test.search.bean;

import java.util.List;

/**
 * Created by admin on 2017/12/7.
 */

public class SearchBean {


    private String msg;
    private RetBean ret;
    private String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RetBean getRet() {
        return ret;
    }

    public void setRet(RetBean ret) {
        this.ret = ret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static class RetBean {
        /**
         * all : 240
         * movieNum : 21
         * pnum : 2
         * totalRecords : 100
         * trailerNum : 1
         * informationNum : 218
         * records : 100
         * otherNum : 0
         */

        private String all;
        private String movieNum;
        private int pnum;
        private int totalRecords;
        private String trailerNum;
        private String informationNum;
        private int records;
        private String otherNum;
        private int totalPnum;
        private List<ListBean> list;

        public String getAll() {
            return all;
        }

        public void setAll(String all) {
            this.all = all;
        }

        public String getMovieNum() {
            return movieNum;
        }

        public void setMovieNum(String movieNum) {
            this.movieNum = movieNum;
        }

        public int getPnum() {
            return pnum;
        }

        public void setPnum(int pnum) {
            this.pnum = pnum;
        }

        public int getTotalRecords() {
            return totalRecords;
        }

        public void setTotalRecords(int totalRecords) {
            this.totalRecords = totalRecords;
        }

        public String getTrailerNum() {
            return trailerNum;
        }

        public void setTrailerNum(String trailerNum) {
            this.trailerNum = trailerNum;
        }

        public String getInformationNum() {
            return informationNum;
        }

        public void setInformationNum(String informationNum) {
            this.informationNum = informationNum;
        }

        public int getRecords() {
            return records;
        }

        public void setRecords(int records) {
            this.records = records;
        }

        public String getOtherNum() {
            return otherNum;
        }

        public void setOtherNum(String otherNum) {
            this.otherNum = otherNum;
        }

        public int getTotalPnum() {
            return totalPnum;
        }

        public void setTotalPnum(int totalPnum) {
            this.totalPnum = totalPnum;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * airTime : 0
             * angleIcon :
             * director :
             * videoType :
             * description : @TV娱乐前线：日前，韩国女艺人南宝拉为某杂志拍摄的一组写真照在网上曝光。
             * pic : http://y3.cnliveimg.com:8080/image/itv/2016/0729/d504a0c2ebe640acbc682ff257430a2f_139148_126.jpg
             * title : 南宝拉性感解禁真空穿白衬衫
             * duration : 00:01:04
             * loadtype : video
             * actors :
             * score : 0
             * dataId : d504a0c2ebe640acbc682ff257430a2f
             * loadURL : http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=d504a0c2ebe640acbc682ff257430a2f
             * shareURL : http://h5.svipmovie.com/zxgl/d504a0c2ebe640acbc682ff257430a2f.shtml?fromTo=shoujimovie
             * region :
             */

            private int airTime;
            private String angleIcon;
            private String director;
            private String videoType;
            private String description;
            private String pic;
            private String title;
            private String duration;
            private String loadtype;
            private String actors;
            private int score;
            private String dataId;
            private String loadURL;
            private String shareURL;
            private String region;

            public int getAirTime() {
                return airTime;
            }

            public void setAirTime(int airTime) {
                this.airTime = airTime;
            }

            public String getAngleIcon() {
                return angleIcon;
            }

            public void setAngleIcon(String angleIcon) {
                this.angleIcon = angleIcon;
            }

            public String getDirector() {
                return director;
            }

            public void setDirector(String director) {
                this.director = director;
            }

            public String getVideoType() {
                return videoType;
            }

            public void setVideoType(String videoType) {
                this.videoType = videoType;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getLoadtype() {
                return loadtype;
            }

            public void setLoadtype(String loadtype) {
                this.loadtype = loadtype;
            }

            public String getActors() {
                return actors;
            }

            public void setActors(String actors) {
                this.actors = actors;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public String getDataId() {
                return dataId;
            }

            public void setDataId(String dataId) {
                this.dataId = dataId;
            }

            public String getLoadURL() {
                return loadURL;
            }

            public void setLoadURL(String loadURL) {
                this.loadURL = loadURL;
            }

            public String getShareURL() {
                return shareURL;
            }

            public void setShareURL(String shareURL) {
                this.shareURL = shareURL;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }
        }
    }
}
