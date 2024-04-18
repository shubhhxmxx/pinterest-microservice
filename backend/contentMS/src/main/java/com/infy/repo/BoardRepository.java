package com.infy.repo;

import java.math.BigInteger;
import java.util.List;

import com.infy.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,BigInteger>{

	List<Board> findAllByUserId(BigInteger userId);

}
