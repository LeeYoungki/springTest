package com.poinblack.videomanager.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "cashbook" , uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class CashBook {

    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "cb_user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "cb_video_id")
    private Video video;

    @OneToOne
    @JoinColumn(name = "cb_rentinfo_id")
    private RentInfo rentInfo;

    @Column(nullable = false)
    private int rentFee;

    @Column(nullable = false)
    private int lateFee;

    @Column(nullable = false)
    private int total;
}
