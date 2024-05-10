package vn.edu.ptit.quiz.exception;

import vn.edu.ptit.quiz.util.message.MessageUtil;

import java.io.Serial;

public class ExistedException extends RuntimeExceptionHandling {
    @Serial
    private static final long serialVersionUID = 1L;

    public ExistedException(String message) {
        super(message + " " + MessageUtil.getMessage("message.existed"));
    }

}
