package com.nielson.carservice.repository;

import com.nielson.carservice.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CarServiceRepository extends JpaRepository <Appointment, Long> {

    List<Appointment> findAllByAppointmentDateBetween(Date dateFrom, Date dateTo);
//         List<Appointment> findAllByAppointmentDateGreaterThanEqualAndAAndAppointmentDateLessThanEqual(LocalDate appointmentStartDate, LocalDate appointmentEndDate);
}
