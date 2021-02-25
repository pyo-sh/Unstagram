package co.kr.datapia.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends CrudRepository<Board, Integer> {
    List<Board> findAllByOrderByReportedDateDesc();

    Optional<Board> findBoardByIdx(Integer id);

    Board save(Board board);

    void deleteBoardByIdx(Integer id);
}
