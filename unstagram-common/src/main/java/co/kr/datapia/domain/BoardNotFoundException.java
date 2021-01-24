package co.kr.datapia.domain;

public class BoardNotFoundException extends RuntimeException {
    public BoardNotFoundException(Integer id) {
        super("Could not find Board " + id.toString());
    }
}
