package com.nielson.carservice.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment{

    @Id
    @GeneratedValue
    private Long id;
    private String carOwnerName;
    private String phoneNumber;

    @NotNull
    private Date appointmentDate;
    private String carRegNumber;
    public double servicePrice;



}
