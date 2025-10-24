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

@Log4j2
@WebServlet(name = "todoReadController", value = "/todo/read")
public class TodoReadController extends HttpServlet {
    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/read의 doGet() 호출");

        // /todo/read?tno=123
        Long tno = Long.parseLong(req.getParameter("tno"));
        try {
            TodoDTO dto = todoService.selectOne(tno);

            if(dto == null) {
                log.info("해당 tno에 해당하는 tuple 존재하지 않음");
                req.setAttribute("dto", "해당 tno에 대한 정보가 존재하지 않음");
            }else{
                req.setAttribute("dto", dto);
            }
            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req,resp);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException("todo read error");
        }
    }
}
