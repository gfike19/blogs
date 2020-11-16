package com.gfike.blogs.dtos;

import com.sun.istack.NotNull;

public class NewBlogDTO {

    @NotNull
    private String title;

    @NotNull
    private String text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
