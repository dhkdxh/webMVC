package com.ssg.webmvc.todo;

import com.ssg.webmvc.todo.dto.TodoDTO;
import com.ssg.webmvc.todo.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@Log4j2
@WebServlet(name = "modifyController", value = "/todo/modify")
public class TodoModifyController extends HttpServlet {
    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/modify의 doGet() 호출");

        req.getRequestDispatcher("/WEB-INF/todo/modify.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/modify의 doPost() 호출");

        Long tno = Long.parseLong(req.getParameter("tno"));
        String title = req.getParameter("title");
        LocalDate dueDate = LocalDate.parse(req.getParameter("dueDate"));
//        boolean finished = Boolean.parseBoolean(req.getParameter("finished"));
        boolean finished = (req.getParameter("finished") != null);

        TodoDTO newDTO = new TodoDTO(tno,title,dueDate,finished);
        try {
            todoService.updateOne(newDTO);

            resp.sendRedirect("/todo/list");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException("todo modify error");
        }
    }
}
