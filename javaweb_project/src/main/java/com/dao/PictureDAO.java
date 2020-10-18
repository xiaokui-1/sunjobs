package com.dao;

import com.pojo.Picture;
import com.util.DBUtil;

import java.util.List;

public class PictureDAO {

    //根据商品的编号查找图片
    public List<Picture> findbygoodsid(String goodsid){
        String sql = "select pid , pname , gid from picture where gid = ?";
        List<Picture> list = DBUtil.query(sql , Picture.class , goodsid);
        return list ;
    }


}
