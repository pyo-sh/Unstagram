package co.kr.datapia.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BoardPictureRepository extends CrudRepository<BoardPicture, Integer> {
    BoardPicture save(BoardPicture boardPicture);

    List<BoardPicture> findAllByBoardIdx(Integer boardIdx);

    Optional<BoardPicture> findByIdx(Integer boardPictureIdx);
}
