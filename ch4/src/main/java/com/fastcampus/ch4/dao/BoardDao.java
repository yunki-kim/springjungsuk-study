package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface BoardDao {
    int insert(BoardDto dto) throws Exception;

    BoardDto select(Integer bno) throws Exception;

    List<BoardDto> selectAll() throws Exception;

    List<BoardDto> selectPage(Map map) throws Exception;

    int update(BoardDto dto) throws Exception;

    int delete(Integer bno, String writer) throws Exception;

    int deleteAll();

    int count() throws Exception;

    int increaseViewCnt(Integer bno) throws Exception;

    List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception;

    int searchResultCnt(SearchCondition sc) throws Exception;

    int updateCommentCnt(Integer bno, int cnt);
}
