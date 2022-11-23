package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.PageHandler;
import com.fastcampus.ch4.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired BoardService boardService;

    @GetMapping("/list")
    public String list(Integer page, Integer pageSize, Model model, HttpServletRequest request) {
        if(!loginCheck(request)) return "redirect:/login/login?toURL=" + request.getRequestURL();

        if (page == null) page = 1;
        if (pageSize == null) pageSize = 10;

        try {
            int totalCnt = boardService.getCount();
            PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);
            Map map = new HashMap();
            map.put("offset", (page - 1) * pageSize);
            map.put("pageSize", pageSize);

            List<BoardDto> list = boardService.getPage(map);
            model.addAttribute("list", list);
            model.addAttribute("ph", pageHandler);
            model.addAttribute("page", page);
            model.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "boardList";
    }

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("mode", "new");
        return "board";
    }

    @PostMapping("/write")
    public String write(BoardDto boardDto, Model model,
                        HttpSession session, RedirectAttributes rattr) {
        String writer = (String) session.getAttribute("id");
        boardDto.setWriter(writer);

        try {
            int rowCnt = boardService.write(boardDto);
            if (rowCnt != 1) {
                throw new Exception("Write fail");
            }
            rattr.addFlashAttribute("msg", "WRT_SUCCESS");

            return "redirect:/board/list";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute(boardDto);
            model.addAttribute("msg", "WRT_FAIL");

            return "board";
        }
    }

    @GetMapping("/read")
    public String read(Integer bno, Integer page, Integer pageSize, Model model) {
        try {
            BoardDto boardDto = boardService.read(bno);
            model.addAttribute(boardDto);
            model.addAttribute("page", page);
            model.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "board";
    }

    @PostMapping("/modify")
    public String modify(BoardDto boardDto, Model model, Integer page, Integer pageSize,
                        HttpSession session, RedirectAttributes rattr) {
        String writer = (String) session.getAttribute("id");
        boardDto.setWriter(writer);

        try {
            model.addAttribute("page", page);
            model.addAttribute("pageSize", pageSize);
            int rowCnt = boardService.modify(boardDto);
            if (rowCnt != 1) {
                throw new Exception("Modify fail");
            }
            rattr.addFlashAttribute("msg", "MOD_SUCCESS");

            return "redirect:/board/list?page=" + page + "&pageSize=" + pageSize;
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute(boardDto);
            model.addAttribute("msg", "MOD_FAIL");

            return "board";
        }
    }

    @PostMapping("/remove")
    public String remove(Integer bno, Integer page, Integer pageSize, Model model,
                         HttpSession session, RedirectAttributes rattr) {
        String writer = (String) session.getAttribute("id");
        try {
            model.addAttribute("page", page);
            model.addAttribute("pageSize", pageSize);
            int rowCnt = boardService.remove(bno, writer);
            if (rowCnt != 1) {
                throw new Exception("board remove error");
            }
            rattr.addFlashAttribute("msg", "DEL_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "DEL_FAIL");
        }
        return "redirect:/board/list?page=" + page + "&pageSize=" + pageSize;
    }

    private boolean loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute("id")!=null;
    }
}
