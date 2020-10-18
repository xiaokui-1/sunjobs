package com.servlet;

import com.dao.UserDAO;
import com.pojo.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/user.do")
public class Userservlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String p = request.getParameter("p");
        if ("fenye".equals(p)){
            doFenye(request,response);
        }
        if ("deletebyusername".equals("p")){
            doDeleteByUsername(request, response);
        }
    }

    private void doDeleteByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");//接收要删除的用户名
        userDAO.deleteByUsername(username);
        doFenye(request, response);
    }

    UserDAO userDAO =new UserDAO();


    private void doFenye(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page=1;
        int size=4;

        String pageString = request.getParameter("page");
        if(pageString!=null && pageString.trim().length()>0){
            page = Integer.parseInt(pageString);
        }

        String sizeString = request.getParameter("size");
        if(sizeString!=null && sizeString.trim().length()>0){
            size = Integer.parseInt(sizeString);
        }

        if(page<1){
            page = 1 ;
        }

        double max =userDAO.max();
        int pagemax= (int) Math.ceil(max/size);
        if (page>pagemax){
            page=pagemax;
        }

        List<UserInfo> list=userDAO.fenye(page,size);
        Map map=new HashMap();
        map.put("page",page);
        map.put("size",size);
        map.put("list",list);
        map.put("pagemax",(int)pagemax);

        request.setAttribute("map",map);
        request.getRequestDispatcher("show.jsp").forward(request,response);




    }
}
