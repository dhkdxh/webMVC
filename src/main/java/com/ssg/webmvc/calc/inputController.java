package com.ssg.webmvc.calc;
//PRG 패턴: POST-REDIRECT-PATTERN
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "inputController", urlPatterns = "/calc/input")
public class inputController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("InputController 요청된 doGet()");
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/calc/input.jsp"); //사용자의 request 정보를 가지고 calc/input.jsp로 이동
        dispatcher.forward(req, resp); //톰캣으로부터 받은 request와 response 들고 이동(전달, 배포하는 역할 객체)


    }
}
