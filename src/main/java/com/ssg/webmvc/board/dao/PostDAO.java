package com.ssg.webmvc.board.dao;

import com.ssg.webmvc.board.domain.PostVO;

import java.util.List;
import java.util.Optional;

public interface PostDAO {
    List<PostVO> findAll();
    boolean countAll();
    Optional<PostVO> findById(long id);
    long save(PostVO post);
    boolean  update(PostVO post);
    boolean  delete(long id);
    boolean checkPassphrase(long id, String passphrase);
}

