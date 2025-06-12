package com.playdata.rediscache.service;

import com.playdata.rediscache.entity.Board;
import com.playdata.rediscache.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> getList(Pageable pageable) {
        Page<Board> pageBoards = boardRepository.findAll(pageable);
        return pageBoards.getContent();
    }

}







