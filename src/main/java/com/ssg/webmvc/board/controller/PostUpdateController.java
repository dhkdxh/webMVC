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
@WebServlet(name = "postUpdateController", value = "/posts/update")
public class PostUpdateController extends HttpServlet {
    private PostService postService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            postService = PostService.getInstance();
            PostDTO postDTO = PostDTO.builder()
                    .postId(Long.parseLong(req.getParameter("postId")))
                    .title(req.getParameter("title"))
                    .content(req.getParameter("content"))
                    .writer(req.getParameter("writer"))
                    .build();

            postService.edit(postDTO, req.getParameter("passphrase"));

            resp.sendRedirect("/posts/view?id=" + postDTO.getPostId());
        } catch (RuntimeException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);
        }
    }
}
