package com.nielson.carservice.service;

import com.nielson.carservice.entity.Appointment;
import com.nielson.carservice.repository.CarServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


@Service
public class CarService {

    private CarServiceRepository repository;

    @Autowired
    public CarService(CarServiceRepository repository){
        this.repository = repository;
    }

    //post methods
    public Appointment saveAppointment( Appointment appointment){
       return repository.save(appointment);
    }

    public List<Appointment> saveAppointments(List<Appointment> appointments){
        return repository.saveAll(appointments);
    }

    //get methods
    public Appointment findAppointmentById(Long id){
        return repository.findById(id).orElse(null);
    }

     public List<Appointment> findAllAppointments(){
         return repository.findAll();
     }

     //get all appointment by date range
    public List<Appointment> getAllAppointmentByDateRange(Date dateFrom , Date dateTo){
         List<Appointment> appointmentList = repository.findAllByAppointmentDateBetween(dateFrom, dateTo);
         appointmentList.sort(Comparator.comparing(Appointment::getServicePrice));
         return appointmentList;
    }


    //delete method
    @Transactional
    public String deleteAppointments(Appointment appointments){
        repository.delete(appointments);
        return "Appointment with owner name:" + " " +appointments.getCarOwnerName()+ " "+ "has been deleted successfully";
    }

    public String deleteAppointmentById(Long id){
        repository.deleteById(id);
        return "Appointment with id:" + " " +id+ " "+ "has been deleted successfully";
    }

    //update methods
    public Appointment updateAppointment(Appointment appointment){
        Appointment existingAppointment = repository.findById(appointment.getId()).orElse(null);
        existingAppointment.setCarOwnerName(appointment.getCarOwnerName());
        existingAppointment.setPhoneNumber(appointment.getPhoneNumber());
        existingAppointment.setAppointmentDate(appointment.getAppointmentDate());
        existingAppointment.setCarRegNumber(appointment.getCarRegNumber());
        existingAppointment.setServicePrice(appointment.getServicePrice());
        return repository.save(existingAppointment);
    }
}
