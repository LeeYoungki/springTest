package com.poinblack.videomanager.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "rent_info" , uniqueConstraints = @UniqueConstraint(columnNames = "rental_id"))
public class RentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private int rental_id;

    @ManyToOne
    @JoinColumn(name = "rent_video_id" )
    private Video video;

    @Column(nullable = false)
    private String state;

    @Column
    private int fee;

    @Column(nullable = false)
    private Date rent_date;

    @ManyToOne
    @JoinColumn(name = "rent_user_id")
    private User user;
}
