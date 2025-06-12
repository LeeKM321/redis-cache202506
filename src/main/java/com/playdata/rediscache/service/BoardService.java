package com.playdata.rediscache.service;

import com.playdata.rediscache.entity.Board;
import com.playdata.rediscache.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    /*
    cacheNames: 캐시 키의 접두사 역할. 키 앞에는 항상 getBoards로 시작하겠다.
    key: boards:page는 기본으로 깔고, 페이지 번호와 size의 숫자를 추가
    #의 의미는 메서드 파라미터를 지목할 때 사용.
    생성된 키: "getBoards:boards:page:1:size:10"
    cacheManager: 여러분이 직접 config로 등록하신 캐시 매니저의 클래스 or 메서드(빈) 이름

    @Cacheble이 달린 메서드는 먼저 캐시를 조회해서 캐시 미스가 난 경우(redis에 데이터가 없는 경우)
    그 때 DB에서 데이터 조회 -> 새로운 캐시 데이터를 redis에 저장까지 자동으로 해 줌.
     */
    @Cacheable(
            cacheNames = "getBoards",
            key = "'boards:page:' + #pageable.pageNumber + ':size:' + #pageable.pageSize",
            cacheManager = "boardCacheManager"
    )
    public List<Board> getList(Pageable pageable) {
        Page<Board> pageBoards = boardRepository.findAll(pageable);
        return pageBoards.getContent();
    }

}







