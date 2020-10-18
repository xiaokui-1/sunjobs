package com.servlet;

import com.dao.GoodsDAO;
import com.dao.PictureDAO;
import com.pojo.Goods;
import com.pojo.Picture;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/goods.do")
public class GoodsServlet extends HttpServlet {
    private GoodsDAO goodsDAO = new GoodsDAO();
    private PictureDAO pictureDAO = new PictureDAO();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String p =request.getParameter("p");
        if ("fenye".equals(p)){
            doFenye(request,response);
        }
        if("findbyid".equals(p)){
            doFindById(request , response);
        }
        if ("addCar".equals(p)){
            doAddCar(request, response);
        }

        if ("deletefromcar".equals(p)) {
            doDeleteFromCar(request, response);
        }

        if ("deletefromcarajax".equals(p)) {
            doDeleteFromCarAjax(request, response);
        }

        if("carnumjian".equals(p)){
            doCarNumJian(request ,response);
        }
    }
    private void doCarNumJian(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String goodsid = request.getParameter("goodsid");
        HttpSession session = request.getSession();
        Goods goods = goodsDAO.findbyid(goodsid);
        Map<Goods, Integer> map = (Map<Goods, Integer>) session.getAttribute("map");
        int n =map.put(goods , map.get(goods)-1);

        if(n>0){
            response.getWriter().println("1");
        }else{
            response.getWriter().println("2");
        }

    }

    //ajax
    private void doDeleteFromCarAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String goodsid = request.getParameter("goodsid");
        HttpSession session = request.getSession();
        Goods goods = goodsDAO.findbyid(goodsid);
        Map<Goods, Integer> map = (Map<Goods, Integer>) session.getAttribute("map");
        int n = map.remove(goods);
        if(n>0){
            response.getWriter().println("1");
        }else{
            response.getWriter().println("2");
        }

    }

    private void doDeleteFromCar(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        Map<Goods, Integer> map = (Map<Goods, Integer>) session.getAttribute("map");
        if (map == null) {
            response.sendRedirect("showCar.jsp");
            return;
        }

        String goodsid = request.getParameter("goodsid");
        Goods goods = goodsDAO.findbyid(goodsid);
        map.remove(goods);
        response.sendRedirect("showCar.jsp");


    }
    private void doAddCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String goodsid = request.getParameter("goodsid");
        String num = request.getParameter("num");

        //得到session
        HttpSession session = request.getSession();


        //先判断该用户有没有购物车
        Map<Goods, Integer> map = (Map<Goods, Integer>) session.getAttribute("map");
        if (map == null) {
            map = new HashMap<Goods, Integer>();
        }

        Goods goods = goodsDAO.findbyid(goodsid);
        //  两次查询   new 两次 ->
        Integer integer = map.get(goods);//判断购物车中是否已经有该商品了

        if (integer == null) {
            map.put(goods, Integer.parseInt(num));
        } else {
            map.put(goods, integer + Integer.parseInt(num));
        }

        session.setAttribute("map", map);
        //实现页面跳转   去显示购物车界面
        //request.getRequestDispatcher("showCar.jsp").forward(request , response);
        response.sendRedirect("showCar.jsp");


    }

    private void doFindById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String goodsid = request.getParameter("goodsid");
        Goods goods = goodsDAO.findbyid(goodsid);
        //还要查询   图片表
        List<Picture> list = pictureDAO.findbygoodsid(goodsid);

        request.setAttribute("goods" , goods);
        request.setAttribute("list" , list);
        request.getRequestDispatcher("showDetail.jsp").forward(request , response);

    }
    private void doFenye(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int size = 4;

        String pageString = request.getParameter("page");

        if (pageString != null && pageString.trim().length() > 0) {
            page = Integer.parseInt(pageString);//把字符串转换为整数
        }

        String sizeString = request.getParameter("size");
        if (sizeString != null && sizeString.trim().length() > 0) {
            size = Integer.parseInt(sizeString);
        }

        if (page < 1) {
            page = 1;
        }

        //查找总条数    查数据库
        int count = goodsDAO.getCount();
        int pageCount = count % size == 0 ? count / size : count / size + 1;
        System.out.println(count);
        if(page>pageCount){
            page = pageCount;
        }

        //查询该页要显示的数据
        List<Goods> list = goodsDAO.fenye(page , size);
//        System.out.println(list);
        Map map = new HashMap();
        map.put("page" , page);
        map.put("size" , size);
        map.put("count" , count);
        map.put("pageCount" , pageCount);
        map.put("list" , list);

        request.setAttribute("map" , map);

        request.getRequestDispatcher("show.jsp").forward(request , response);

    }
}
