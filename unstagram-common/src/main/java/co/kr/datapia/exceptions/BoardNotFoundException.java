package co.kr.datapia.exceptions;

public class BoardNotFoundException extends RuntimeException {
    public BoardNotFoundException(Integer id) {
        super("Could not find Board " + id.toString());
    }
}
