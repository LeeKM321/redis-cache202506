package com.playdata.rediscache.controller;

import com.playdata.rediscache.entity.Board;
import com.playdata.rediscache.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public List<Board> getList(Pageable pageable) {
        log.info("/list: GET, pageable: {}", pageable);
        return boardService.getList(pageable);
    }


}









