package com.servelet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user.do")
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        response.setContentType("text/html;charset=utf-8");

        String p=request.getParameter("p");
        if ("regist".equals(p)){
            doRegist(request,response);
        }
    }

    private void doRegist(HttpServletRequest request, HttpServletResponse response) {
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        System.out.println(username);
        System.out.println(password);

        String[] loves = request.getParameterValues("loves");
        if (loves != null){

        }

    }
}
