package com.poinblack.videomanager.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "cashbook" , uniqueConstraints = @UniqueConstraint(columnNames = "cb_id"))
public class CashBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cb_id")
    private int cb_id;

    @ManyToOne
    @JoinColumn(name = "cb_user_id" )
    private User user;

    @ManyToOne
    @JoinColumn(name = "cb_video_id" )
    private Video video;

    @Column(nullable = false)
    private int rent_fee;

    @Column(nullable = false)
    private int late_fee;

    @Column(nullable = false)
    private int total;
}
