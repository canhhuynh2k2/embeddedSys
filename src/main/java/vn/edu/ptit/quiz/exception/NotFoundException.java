package vn.edu.ptit.quiz.exception;

import vn.edu.ptit.quiz.util.message.MessageUtil;

import java.io.Serial;

public class NotFoundException extends RuntimeExceptionHandling {

    @Serial
    private static final long serialVersionUID = 161640254077785989L;

    public NotFoundException(String message) {
        super(message + " " + MessageUtil.getMessage("message.notFound"));
    }

}