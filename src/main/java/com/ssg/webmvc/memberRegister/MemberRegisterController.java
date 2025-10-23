package com.ssg.webmvc.memberRegister;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "MemberRegisterController", value = "/register1")
public class MemberRegisterController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        MemberVo member = new MemberVo();

        member.setId(req.getParameter("user_id"));
        member.setPwd(req.getParameter("user_pwd"));
        member.setSex(req.getParameter("user_sex"));
        String[] userHobbies = req.getParameterValues("user_hobby");
        String hobbiesStr = "";

        for (int i = 0; i < userHobbies.length - 1; i++) {
            hobbiesStr += (userHobbies[i] + ",");
        }
        hobbiesStr += userHobbies[userHobbies.length - 1];
        member.setHobbies(hobbiesStr);

        MemberDao memberDao = new MemberDao();
        boolean result = memberDao.insertMember(member);

         if(result) {
             req.setAttribute("msg", member.getId()+"님이 회원가입에 성공하였습니다.");
         }else{
             req.setAttribute("msg", "다시 시도해주세요.");
         }

         req.getRequestDispatcher("/WEB-INF/result.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init() throws ServletException {

    }
}
