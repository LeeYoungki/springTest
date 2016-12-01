package com.poinblack.videomanager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "user" , uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class User {

    @Id
    @Column(name = "id")
    private int id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    //Todo hibernate 활용방법 구현 시 삭제(아래 전부)
    @Column
    private int rentFee;

    @Column
    private int lateFee;

    public void addRentFee(int rentFee){
        this.rentFee += rentFee;
    }

    public void addLateFee(int lateFee){
        this.lateFee += lateFee;
    }

}
