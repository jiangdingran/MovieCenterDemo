package com.jx.test.welfare.bean;

import java.util.List;

/**
 * Created by w h l on 2017/12/6.
 */

public class WelfareBean {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-11-30 08:00","title":"宅男尤物 李猩一Hjyamber 波涛胸涌大尺度爱的捆缚蒙眼SM调教诱惑","description":"美女写真","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2016/11/m.xxxiao.com_3d76a64f45f606cd60e266b598346de1-683x1024.jpg","url":"http://m.xxxiao.com/98234"},{"ctime":"2016-11-30 08:00","title":"头条女神 许灵鑫Yenn亚鑫 宅家惹火喷血女仆性感诱惑","description":"美女写真","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2016/11/m.xxxiao.com_a659ec282c4172365480d99f2e8c2281-683x1024.jpg","url":"http://m.xxxiao.com/98233"},{"ctime":"2016-11-30 08:00","title":"I邻家女孩\u2026\u2026[双生姐妹 大小乔 131p]\u2026\u2026第九百四十九辑（附往辑链接）","description":"华声美女","picUrl":"http://image.hnol.net/c/2016-11/29/22/201611292224159831-1559530.jpg","url":"http://bbs.voc.com.cn/mm/meinv-7562704-0-1.html"},{"ctime":"2016-11-30 08:00","title":"I邻家女孩\u2026\u2026[寤寐思服 孙佳歆 126p]\u2026\u2026第九百四十八辑","description":"华声美女","picUrl":"http://image.hnol.net/c/2016-11/29/21/201611292152113691-1559530.jpg","url":"http://bbs.voc.com.cn/mm/meinv-7562672-0-1.html"},{"ctime":"2016-11-30 08:00","title":"I邻家女孩\u2026\u2026[秀色可餐 张嘉庭 52p]\u2026\u2026第九百五十辑","description":"华声美女","picUrl":"http://image.hnol.net/c/2016-11/29/23/201611292308296891-1559530.jpg","url":"http://bbs.voc.com.cn/mm/meinv-7562750-0-1.html"},{"ctime":"2016-11-30 10:00","title":"爱秀 阿曼Amanda 2016-11-29","description":"华声美女","picUrl":"http://image.hnol.net/c/2016-11/29/23/201611292310095291-5058976.jpg","url":"http://bbs.voc.com.cn/mm/meinv-7562755-0-1.html"},{"ctime":"2016-11-30 10:00","title":"[贴图][推荐]黑蕾丝性感","description":"华声美女","picUrl":"http://image.hnol.net/c/2016-11/30/07/201611300720209911-5381008.jpg","url":"http://bbs.voc.com.cn/mm/meinv-7562914-0-1.html"},{"ctime":"2016-11-30 10:00","title":"丽柜 川儿 Model( 2016.11.28)","description":"华声美女","picUrl":"http://image.hnol.net/c/2016-11/30/02/201611300235328771-5058976.jpg","url":"http://bbs.voc.com.cn/mm/meinv-7562910-0-1.html"},{"ctime":"2016-11-30 10:00","title":"丽柜 Model 文静(2016.11.30)","description":"华声美女","picUrl":"http://image.hnol.net/c/2016-11/30/02/2016113002211741910-5058976.jpg","url":"http://bbs.voc.com.cn/mm/meinv-7562909-0-1.html"},{"ctime":"2016-11-30 12:00","title":"[贴图]清秀文雅靓女","description":"华声美女","picUrl":"http://image.hnol.net/c/2016-11/30/10/201611301037113791-4217076.jpg","url":"http://bbs.voc.com.cn/mm/meinv-7563310-0-1.html"}]
     */

    private String code;
    private String msg;
    private List<NewslistBean> newslist;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * ctime : 2016-11-30 08:00
         * title : 宅男尤物 李猩一Hjyamber 波涛胸涌大尺度爱的捆缚蒙眼SM调教诱惑
         * description : 美女写真
         * picUrl : http://m.xxxiao.com/wp-content/uploads/sites/3/2016/11/m.xxxiao.com_3d76a64f45f606cd60e266b598346de1-683x1024.jpg
         * url : http://m.xxxiao.com/98234
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
