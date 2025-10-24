package com.ssg.webmvc.todo;

import com.ssg.webmvc.todo.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet(name = "removeController", value = "/todo/remove")
public class TodoRemoveController extends HttpServlet {
    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/remove의 doPost() 호출");

        Long tno = Long.parseLong(req.getParameter("tno"));
        try {
            todoService.deleteOne(tno);

            resp.sendRedirect("/todo/list");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException("todo remove error");
        }
    }
}
