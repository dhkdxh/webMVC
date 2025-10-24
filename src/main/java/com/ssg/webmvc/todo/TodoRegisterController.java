package com.ssg.webmvc.todo;

import com.ssg.webmvc.todo.dto.TodoDTO;
import com.ssg.webmvc.todo.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@Log4j2
@WebServlet(name = "todoRegisterController",value = "/todo/register")
public class TodoRegisterController extends HttpServlet {
    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/register doGet 실행");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/todo/register.jsp");
        dispatcher.forward(req,resp);
        //req.getRequestDispatch("url").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/register doPost 실행");

        TodoDTO dto = new TodoDTO();

        dto.setTitle(req.getParameter("title"));
        dto.setDueDate(LocalDate.parse(req.getParameter("dueDate")));
        dto.setFinished(false);

        try {
            todoService.register(dto);

            resp.sendRedirect("/todo/list");
            //브라우저가 반복적인 post를 못하도록 막아줌
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException("todo register error");
        }
    }
}

//PRG의 가장 기본적인 Pattern