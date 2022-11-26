package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.CommentDto;
import com.fastcampus.ch4.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CommentController {

    @Autowired CommentService commentService;

    // 댓글을 등록하는 메서드
    @PostMapping("/comments") // comments?bno=  POST
    public ResponseEntity<String> write(@RequestBody CommentDto commentDto, Integer bno, HttpSession session) {
        // String commenter = (String) session.getAttribute("id");
        String commenter = "asdf";
        commentDto.setCommenter(commenter);
        commentDto.setBno(bno);

        try {
            if (commentService.write(commentDto) != 1) {
                throw new Exception("Write fail");
            }
            return new ResponseEntity<>("WRT_SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("WRT_FAIL", HttpStatus.BAD_REQUEST);
        }
    }

    // 지정된 게시물의 모든 댓글을 가져오는 메서드
    @GetMapping("/comments") // comments?bno=   GET
    public ResponseEntity<List<CommentDto>> list(Integer bno) {
        List<CommentDto> list = null;
        try {
            list = commentService.getList(bno);
            return new ResponseEntity<>(list, HttpStatus.OK); // 200
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400
        }
    }

    // 댓글을 수정하는 메서드
    @PatchMapping("/comments/{cno}") // coments/{}  PATCH
    public ResponseEntity<String> modify(@RequestBody CommentDto commentDto,
                                         @PathVariable Integer cno) {
        // String commenter = (String) session.getAttribute("id");
        commentDto.setCno(cno);

        try {
            if (commentService.modify(commentDto) != 1) {
                throw new Exception("Modify fail");
            }
            return new ResponseEntity<>("MOD_SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("MOD_FAIL", HttpStatus.BAD_REQUEST);
        }
    }

    // 지정된 댓글을 삭제하는 메서드
    @DeleteMapping("/comments/{cno}") // comments/{}    DELETE
    public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session) {
        // String commenter = (String) session.getAttribute("id");
        String commenter = "asdf";
        try {
            int rowCnt = commentService.remove(cno, bno, commenter);
            if (rowCnt != 1) throw new Exception("Delete Fail");

            return new ResponseEntity<>("DEL_SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("DEL_FAIL", HttpStatus.BAD_REQUEST);
        }
    }
}
