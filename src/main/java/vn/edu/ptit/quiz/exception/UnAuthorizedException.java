package vn.edu.ptit.quiz.exception;

import vn.edu.ptit.quiz.util.message.MessageUtil;

import java.io.Serial;

public class UnAuthorizedException extends RuntimeExceptionHandling {

    @Serial
    private static final long serialVersionUID = 1616402540777823136L;

    public UnAuthorizedException() {
        super(MessageUtil.getMessage("message.unauthorized"));
    }

    public UnAuthorizedException(String message) {
        super(message + " " + MessageUtil.getMessage("message.unauthorized"));
    }
}