package com.ssg.webmvc.calc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "calcController", value ="/calc/makeResult")
public class calcController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num2");

        System.out.println(num1 + " " + num2);

        //POST 처리 후 원하는 곳으로 이동하기 위해 GET 방식 채택
        resp.sendRedirect("/calc/input"); //응답에 대한 sendRedirect 사용(req와 resp 사용하지 않은 상태에서 이동)
    }
}
