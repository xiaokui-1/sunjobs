package com.dao;

import com.pojo.Collection;
import com.pojo.UserInfo;
import com.util.DBUtil;

import java.util.List;

public class UserDAO {
    public UserInfo login(String username,String password){
        String sql="select username,password from userinfo where username=? and password=?";
        List<UserInfo> list= DBUtil.query(sql,UserInfo.class,username,password);
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }
    //判断用户是否已经收藏
    public Collection check(String username , String goodsid){
        String sql = "select username , goodsid from collection where username = ? and goodsid = ? ";
        List<Collection> list = DBUtil.query(sql , Collection.class , username , goodsid);

        if(list.size()>0){
            return list.get(0);
        }
        return null ;
    }

    //收藏
    public int addCollection(String username , String goodsid){
        String sql = "insert into collection(username , goodsid) values(?,?)";
        int n = DBUtil.zsg(sql , username , goodsid);
        return n ;
    }

}
