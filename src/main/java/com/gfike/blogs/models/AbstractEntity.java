package com.gfike.blogs.models;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public class AbstractEntity {

    @Id
    @Column(updatable = false, nullable = false)
    private int id;

    @Column(updatable = false, nullable = false, name="date_created")
    private String dateCreated;

    @PrePersist
    public void autofill() {
        UUID uuid = UUID.randomUUID();
        this.id = (int)UUID.randomUUID().getMostSignificantBits();
        Date dt = new java.util.Date();
        SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        this.dateCreated = currentTime;
    }

    public int getId() {
        return this.id;
    }

    public String getDateCreated() {
        return this.dateCreated;
    }
}
