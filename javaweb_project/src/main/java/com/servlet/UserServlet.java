package com.servlet;

import com.dao.UserDAO;
import com.pojo.Collection;
import com.pojo.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user.do")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String p=request.getParameter("p");
        if ("login".equals(p)){
            doLogin(request,response);
        }
        if ("addCollection".equals(p)) {
            doAddCollection(request, response);
        }
    }

    private void doAddCollection(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //收藏之前    得先判断用户有没有登录
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        if (userInfo == null) {
            response.getWriter().println("<script>alert('请登录先！');location='login.jsp'</script>");
            return;
        }

        //判断用户是否已经收藏了改商品
        String goodsid = request.getParameter("goodsid");
        String username = userInfo.getUsername();
        Collection collection = userDAO.check(username , goodsid);

        //收藏了怎么
        if(collection!=null){
            response.getWriter().println("<script>alert('您已经收藏');location='goods.do?p=findbyid&goodsid="+goodsid+"'</script>");
            return ;
        }

        //没有收藏
        int n = userDAO.addCollection(username, goodsid);
        if(n>0){
            response.getWriter().println("<script>alert('收藏成功');location='goods.do?p=findbyid&goodsid="+goodsid+"'</script>");
        }
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        UserInfo userInfo = userDAO.login(username, password);
        if (userInfo == null) {
            response.getWriter().println("<script>alert('用户名或者密码错误！');location='login.jsp';</script>");
            return;
        } else {
            //登录成功
            request.getSession().setAttribute("userinfo", userInfo);
            request.getRequestDispatcher("index.jsp").forward(request, response);

        }
    }
}
