package com.dao;

import com.pojo.UserInfo;
import com.util.DBUtil;

import java.util.List;

public class UserDAO {
    public List<UserInfo> fenye(int page,int size){
        String sql="select username,password from userinfo limit ?,?";
        List<UserInfo> list = DBUtil.query(sql,UserInfo.class,(page-1)*size,size);
        return list;
    }
    public double max(){
        String sql="select count(*) from userinfo";
        double max =DBUtil.uniqueQuery(sql);
        return max;
    }
    public int deleteByUsername(String username){
        String sql = "delete from userinfo where username = ? ";
        int n = DBUtil.zsg(sql , username);
        return n;
    }


}
