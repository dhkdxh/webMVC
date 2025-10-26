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
@WebServlet(name = "postDeleteController", value = "/posts/delete")
public class PostDeleteController extends HttpServlet {
    private PostService postService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        postService = PostService.getInstance();

        String passphrase = req.getParameter("passphrase");
        Long postId = Long.parseLong(req.getParameter("id"));

        try{
            postService.remove(postId,passphrase);

            resp.sendRedirect("/posts");
        } catch (RuntimeException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);
        }
    }
}
