package com.sqlva.springboot.s;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    public Message(String aaaaaa, String ccccccc) {
        this.title = aaaaaa;
        this.content = ccccccc;
    }
}
