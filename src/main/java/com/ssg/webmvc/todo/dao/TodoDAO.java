package com.ssg.webmvc.todo.dao;

import com.ssg.webmvc.todo.domain.TodoVO;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {
    public String getTime() {
        String now = null;

        try (Connection connection = ConnectionUtil.INSTANCE.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("select now()");
             ResultSet rs = pstmt.executeQuery()) {

            rs.next();
            now = rs.getString(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    public String getTime2() throws Exception {
        @Cleanup
        Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement("select now()");
        @Cleanup ResultSet rs = pstmt.executeQuery();

        rs.next();
        return rs.getString(1);
    }

    public void insert(TodoVO vo) throws Exception {
        String sql = "insert into todo(title,dueDate,finished) values(?,?,?)";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, vo.getTitle());
        pstmt.setDate(2, Date.valueOf(vo.getDueDate()));
        pstmt.setBoolean(3, vo.isFinished());
        pstmt.executeUpdate();
    }

    public List<TodoVO> selectAll() throws Exception {
        String sql = "select * from todo";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
        @Cleanup ResultSet rs = pstmt.executeQuery();

        List<TodoVO> todoList = new ArrayList<>();
        while (rs.next()) {
            TodoVO vo = TodoVO.builder().
                    tno(rs.getLong("tno")).
                    title(rs.getString("title")).
                    dueDate(rs.getDate("dueDate").toLocalDate()).
                    finished(rs.getBoolean("finished"))
                    .build();

            todoList.add(vo);
        }
        return todoList;
    }

    public TodoVO selectOne(Long tno) throws Exception {
        String sql = "select * from todo where tno = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setLong(1, tno);
        @Cleanup ResultSet rs = pstmt.executeQuery();

        rs.next();

        TodoVO vo = TodoVO.builder().
                tno(rs.getLong("tno")).
                title(rs.getString("title")).
                dueDate(rs.getDate("dueDate").toLocalDate()).
                finished(rs.getBoolean("finished")).
                build();

        return vo;
    }

    public void deleteOne(Long tno) throws Exception {
        String sql = "delete from todo where tno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setLong(1, tno);
        pstmt.executeUpdate();
    }

    public void updateOne(TodoVO todoVO) throws Exception{
        String sql = "update todo set title = ?,dueDate = ?, finished = ? where tno = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, todoVO.getTitle());
        pstmt.setDate(2, Date.valueOf(todoVO.getDueDate()));
        pstmt.setBoolean(3, todoVO.isFinished());
        pstmt.setLong(4, todoVO.getTno());
        pstmt.executeUpdate();
    }
}
