package com.bawei.base;

/*
 *@auther:邓先超
 *@Date: 2019/12/28
 *@Time:9:00
 *@Description:
 **/
public class BasePresenter<V extends BaseFragment> {
    public V v;

    public void attach(V v){
        this.v=v;
    }

    public void unattach(){
        if(v!=null){
            v=null;
        }
    }
}
