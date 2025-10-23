package com.ssg.webmvc.todo;

import com.ssg.webmvc.todo.dto.TodoDTO;
import com.ssg.webmvc.todo.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "todoListController", value = "/todo/list")
public class TodoListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet() 호출");

        List<TodoDTO> todoDTOList = TodoService.INSTANCE.getList();

        req.setAttribute("list", todoDTOList); //req에 todoDTOList를 담아 forward
        req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req,resp);
    }
}
