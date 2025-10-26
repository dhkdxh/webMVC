package com.ssg.webmvc.board.controller;

import com.ssg.webmvc.board.dto.PostDTO;
import com.ssg.webmvc.board.service.PostService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet(name = "postEditFormController", value = "/posts/edit")
public class PostEditFormController extends HttpServlet {
    private PostService postService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            postService = PostService.getInstance();

            Long postId= Long.parseLong(req.getParameter("id"));

            PostDTO postDTO = postService.getDetail(postId);

            req.setAttribute("post", postDTO);
            req.getRequestDispatcher("/WEB-INF/view/form.jsp").forward(req, resp);
        } catch (RuntimeException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);
        }
    }
}
