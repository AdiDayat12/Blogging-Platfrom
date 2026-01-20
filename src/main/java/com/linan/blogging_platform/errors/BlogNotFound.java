package com.linan.blogging_platform.errors;

public class BlogNotFound extends RuntimeException{
    public BlogNotFound (String message) {
        super(message);
    }
}
