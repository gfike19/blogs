package com.gfike.blogs.models;

import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Blog extends AbstractEntity {

    @Column(name="blog_title")
    @NotNull
    private String title;

    @Column(name="blog_text")
    @NotNull
    private String text;

    public Blog(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Blog() {
    }

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
