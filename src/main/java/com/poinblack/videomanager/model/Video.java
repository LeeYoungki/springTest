package com.poinblack.videomanager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "video" , uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Video {

    @Id
    @Column(name = "id")
    private int id;

    @Column(nullable = false)
    private String vid;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int rentFee;

    @Column(nullable = false)
    private int lateFee;

    @Column(nullable = false)
    private String state;
}
