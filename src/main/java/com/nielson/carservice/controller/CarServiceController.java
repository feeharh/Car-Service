package com.nielson.carservice.controller;

import com.nielson.carservice.entity.Appointment;
import com.nielson.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class CarServiceController {

    private CarService service;

    @Autowired
    public CarServiceController(CarService service){
        this.service = service;
    }

    @PostMapping("/addAppointment")
    public Appointment addAppointment(@RequestBody Appointment appointment){
        return service.saveAppointment(appointment);
    }

    @PostMapping("/addAppointments")
    public List<Appointment> addAppointments(@RequestBody List<Appointment> appointments){
        return  service.saveAppointments(appointments);
    }

    @GetMapping("/appointment/{id}")
    public Appointment getAppointmentById(@PathVariable Long id){
        return service.findAppointmentById(id);
    }

    @GetMapping("/appointment")
    public List<Appointment> getAppointments(){
        return service.findAllAppointments();
    }

    @GetMapping("/appointment/{dateFrom}/{dateTo}")
    public List <Appointment> getAppointmentByDateRange(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom ,
                                                        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo){
        return service.getAllAppointmentByDateRange(dateFrom, dateTo);
    }

    @DeleteMapping("/delete")
    public String delete(@RequestBody Appointment appointment){
        return service.deleteAppointments(appointment);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id){
        return service.deleteAppointmentById(id);
    }

    @PutMapping("/update")
    public Appointment update(@RequestBody Appointment appointment){
        return service.updateAppointment(appointment);
    }
}
