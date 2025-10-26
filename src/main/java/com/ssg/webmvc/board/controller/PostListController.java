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
@WebServlet(name = "postListController", value = "/posts")
public class PostListController extends HttpServlet {
    private PostService postService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            log.info("list doGet call");
            postService = PostService.getInstance();

            List<PostDTO> posts = postService.getList();

            req.setAttribute("posts", posts);
            req.getRequestDispatcher("/WEB-INF/view/list.jsp").forward(req, resp);
        } catch (RuntimeException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);
        }
    }
}
