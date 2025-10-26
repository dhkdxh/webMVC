package com.ssg.webmvc.board.dao;

import com.ssg.webmvc.board.domain.PostVO;
import com.ssg.webmvc.todo.dao.ConnectionUtil;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class PostDAOImpl implements PostDAO {
    private static PostDAOImpl postDAO;

    private PostDAOImpl() {}

    public static PostDAOImpl getInstance() {
        if (postDAO == null) postDAO = new PostDAOImpl();
        return postDAO;
    }

    private Connection conn;

    private void closeConnection(){
        try{
            if(conn != null){
                conn.close();
            }
        }catch (SQLException e){throw new RuntimeException(e);}
    }

    @Override
    public List<PostVO> findAll() {
        try {
            conn = ConnectionUtil.INSTANCE.getConnection();
        } catch (Exception e) {
            log.error("DB connection error");
            closeConnection();
            throw new RuntimeException(e.getMessage());
        }

        List<PostVO> posts = new ArrayList<>();
        String sql = "select * from board_post";

        try(PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()){

            while(rs.next()){
                Long id = rs.getLong("post_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String writer = rs.getString("writer");
                String passphrase = rs.getString("passphrase");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                LocalDateTime updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();

                PostVO vo = PostVO.builder()
                        .postId(id)
                        .title(title)
                        .content(content)
                        .writer(writer)
                        .passphrase(passphrase)
                        .createdAt(createdAt)
                        .updatedAt(updatedAt)
                        .build();
                posts.add(vo);
            }
        }catch (SQLException e){
            log.error("SQLException: " + e.getMessage());
            closeConnection();
            throw new RuntimeException(e.getMessage());
        }
        return posts;
    }

    @Override
    public boolean countAll() {
        return false;
    }

    @Override
    public Optional<PostVO> findById(long id) {
        try {
            conn = ConnectionUtil.INSTANCE.getConnection();
        } catch (Exception e) {
            log.error("DB connection error");
            closeConnection();
            throw new RuntimeException(e.getMessage());
        }

        String sql = "select * from board_post where post_id = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1, id);
            try(ResultSet rs = pstmt.executeQuery()){
                if(!rs.next()) return Optional.empty();

                PostVO vo = PostVO.builder()
                        .postId(rs.getLong("post_id"))
                        .title(rs.getString("title"))
                        .content(rs.getString("content"))
                        .writer(rs.getString("writer"))
                        .passphrase(rs.getString("passphrase"))
                        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                        .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
                        .build();
                return Optional.of(vo);
            }
        }catch (SQLException e){
            log.error("SQLException: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public long save(PostVO post) {
        try {
            conn = ConnectionUtil.INSTANCE.getConnection();
        } catch (Exception e) {
            log.error("DB connection error");
            closeConnection();
            throw new RuntimeException(e.getMessage());
        }

        String sql = "insert into board_post (title,content,writer,passphrase) values(?,?,?,?)";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getContent());
            pstmt.setString(3, post.getWriter());
            pstmt.setString(4, post.getPassphrase());

            int result = pstmt.executeUpdate();

            return Long.parseLong(String.valueOf(result));
        }catch (SQLException e){
            log.error("SQLException: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean update(PostVO post) {
        try {
            conn = ConnectionUtil.INSTANCE.getConnection();
        } catch (Exception e) {
            log.error("DB connection error");
            closeConnection();
            throw new RuntimeException(e.getMessage());
        }

        String sql = "update board_post set title = ?,content = ? where post_id = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getContent());
            pstmt.setLong(3, post.getPostId());

            int result = pstmt.executeUpdate();
            return (result > 0);
        }catch (SQLException e){
            log.error("SQLException: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean delete(long id) {
        try {
            conn = ConnectionUtil.INSTANCE.getConnection();
        } catch (Exception e) {
            log.error("DB connection error");
            closeConnection();
            throw new RuntimeException(e.getMessage());
        }

        String sql = "delete from board_post where post_id = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1, id);

            int result = pstmt.executeUpdate();

            return (result > 0);
        }catch (SQLException e){
            log.error("SQLException: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean checkPassphrase(long id, String passphrase) {
        try {
            conn = ConnectionUtil.INSTANCE.getConnection();
        } catch (Exception e) {
            log.error("DB connection error");
            closeConnection();
            throw new RuntimeException(e.getMessage());
        }

        String sql = "select passphrase from board_post where post_id = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            rs.next();
            String pwd = rs.getString("passphrase");

            return pwd.equals(passphrase);
        }catch (SQLException e){
            log.error("SQLException: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
