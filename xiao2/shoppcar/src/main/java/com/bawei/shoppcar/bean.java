package com.bawei.shoppcar;

import java.util.List;

/*
 *@auther:邓先超
 *@Date: 2019/12/24
 *@Time:20:47
 *@Description:
 **/
public class bean {



    private int code;
    private String message;
    private List<CategoryBean> category;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CategoryBean> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryBean> category) {
        this.category = category;
    }

    public static class CategoryBean {


        private String clazz;
        private List<ChildsBean> childs;

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }

        public List<ChildsBean> getChilds() {
            return childs;
        }

        public void setChilds(List<ChildsBean> childs) {
            this.childs = childs;
        }

        public static class ChildsBean {
            /**
             * avatar : http://blog.zhaoliang5156.cn/api/images/head_zhu_2019_08_01.jpeg
             * name : 小美1
             */

            private String avatar;
            private String name;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
