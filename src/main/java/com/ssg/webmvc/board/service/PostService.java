package com.ssg.webmvc.board.service;

import com.ssg.webmvc.board.dao.PostDAO;
import com.ssg.webmvc.board.dao.PostDAOImpl;
import com.ssg.webmvc.board.domain.PostVO;
import com.ssg.webmvc.board.dto.PostDTO;
import com.ssg.webmvc.todo.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class PostService {
    private final PostDAO dao;
    private ModelMapper modelMapper;

    private static PostService postService;
    private PostService(){
        dao = PostDAOImpl.getInstance();
        modelMapper = MapperUtil.INSTANCE.get();
    }
    public static PostService getInstance(){
        if(postService == null) postService = new PostService();
        return postService;
    }

    public List<PostDTO> getList() throws RuntimeException {
        List<PostVO> voPosts = dao.findAll();

        List<PostDTO> dtoPosts = voPosts.stream().map(
                i -> modelMapper.map(i,PostDTO.class)
        ).collect(Collectors.toList());

        return dtoPosts;
    }

    public PostDTO getDetail(long id) throws RuntimeException {
        PostVO post = dao.findById(id).orElseThrow(() -> new RuntimeException("게시물이 존재하지 않습니다."));

        PostDTO postDTO = modelMapper.map(post, PostDTO.class);

        return postDTO;
    }
    // 조회수 증가 포함


    public long write(PostDTO post) throws RuntimeException {
        if(post.getTitle().length()<2 || post.getTitle().length()>200) throw new RuntimeException("제목은 2-200자만 허용됩니다.");

        if(post.getWriter().length()<1 || post.getWriter().length()>50) throw new RuntimeException("작성자는 1-50자만 허용됩니다");

        if(post.getContent().length()<5) throw new RuntimeException("내용은 5자 이상이어야 합니다.");

        if(post.getPassphrase().length()<4 || post.getPassphrase().length()>20) throw new RuntimeException("비밀번호는 4-20만 허용됩니다.");

        //모두 유효한 경우
        PostVO voPost = modelMapper.map(post, PostVO.class);

        return dao.save(voPost);
    }// 검증 + 저장

    public void edit(PostDTO post, String passphrase) throws RuntimeException {
        boolean result = dao.checkPassphrase(post.getPostId(), passphrase);

        if(!result) throw new RuntimeException("비밀번호가 맞지 않습니다.");

        post.setPassphrase(passphrase);
        PostVO voPost = modelMapper.map(post, PostVO.class);
        result = dao.update(voPost);

        if(!result) throw new RuntimeException("수정 중 예외가 발생하였습니다.");
    }  // 비번검증 + 수정


    public void remove(long id, String passphrase) throws RuntimeException {
        boolean checked = dao.checkPassphrase(id, passphrase);

        if(!checked) throw new RuntimeException("비밀번호가 맞지 않습니다.");

        boolean result = dao.delete(id);

        if(!result) throw new RuntimeException("삭제가 되지 않았습니다.");
    }
}
