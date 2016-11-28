package com.poinblack.videomanager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "user" , uniqueConstraints = @UniqueConstraint(columnNames = "user_id"))
public class User {

    @Id
    @Column(name = "user_id")
    private String user_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    //Todo hibernate 활용방법 구현 시 삭제(아래 전부)
    @Column
    private int rent_fee;

    @Column
    private int late_fee;

    public void addRentFee(int rent_fee){
        this.rent_fee += rent_fee;
    }

    public void addLateFee(int late_fee){
        this.late_fee += late_fee;
    }

}
