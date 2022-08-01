package com.heybys.jpatest.sub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "use_yn", nullable = false)
    @Type(type = "yes_no")
    private Boolean useYn;

}
