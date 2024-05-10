package vn.edu.ptit.quiz.exception;

import vn.edu.ptit.quiz.util.message.MessageUtil;

public class FileNotFoundException extends RuntimeExceptionHandling {
    public FileNotFoundException(String message) {
        super(message + MessageUtil.getMessage("message.fileNotFound"));
    }
}