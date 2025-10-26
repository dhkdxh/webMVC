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

@Log4j2
@WebServlet(name = "postSaveController",value = "/posts/save")
public class PostSaveController extends HttpServlet {
    private PostService postService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            postService = PostService.getInstance();

            String title = req.getParameter("title");
            String content = req.getParameter("content");
            String writer = req.getParameter("writer");
            String passphrase = req.getParameter("passphrase");

            PostDTO postDTO = PostDTO.builder()
                    .title(title)
                    .content(content)
                    .writer(writer)
                    .passphrase(passphrase)
                    .build();

            postService.write(postDTO);
            resp.sendRedirect("/posts");

        } catch (RuntimeException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);
        }
    }
}
