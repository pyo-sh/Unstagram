package co.kr.datapia.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends CrudRepository<Board, Integer> {
    List<Board> findAll();

    Optional<Board> findBoardByID(Integer id);

    Board save(Board board);
}
