package com.playdata.rediscache.repository;

import com.playdata.rediscache.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
