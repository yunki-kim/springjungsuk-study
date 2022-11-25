package com.fastcampus.ch4.service;

import com.fastcampus.ch4.domain.CommentDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentService {
    @Transactional(rollbackFor = Exception.class)
    int write(CommentDto commentDto) throws Exception;

    CommentDto read(Integer cno) throws Exception;

    List<CommentDto> getList(Integer bno) throws Exception;

    int modify(CommentDto commentDto) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    int remove(Integer cno, Integer bno, String commenter) throws Exception;

    int getCount(Integer bno) throws Exception;
}
