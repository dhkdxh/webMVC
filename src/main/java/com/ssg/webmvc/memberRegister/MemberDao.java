package com.ssg.webmvc.memberRegister;

import java.sql.*;

public class MemberDao {
    private Statement stmt;
    private Connection conn;

    private void connDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("드라이버 로딩 성공");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?serverTimezone=Asia/Seoul", "root", "wjsdPdnjs0404!");

            System.out.println("Connection 생성 성공");
            stmt = conn.createStatement();
            System.out.println("Statement 생성 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버가 존재하지 않습니다");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insertMember(MemberVo member){
        boolean value = false;
        try {
            connDB();
            String sql = "insert into memberInfo values(?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPwd());
            pstmt.setString(3, member.getSex());
            pstmt.setString(4, member.getHobbies());

            int result = pstmt.executeUpdate();
            if(result != 0) value = true;

            pstmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }
}
