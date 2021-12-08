package com.nielson.carservice.controller;

import com.nielson.carservice.entity.Appointment;
import com.nielson.carservice.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

public class CarServiceControllerTest {

    public CarServiceController controller;

    @Mock
    public CarService service;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(CarServiceControllerTest.this);
        controller = new CarServiceController(service);
    }

    @DisplayName("Test for add appointment controller")
    @Test
    public void addAppointmentTest(){
        Date appointmentDate = new Date(2021, 6, 12);
        Appointment appointment = new Appointment(2L, "paul" , "787-565-5666", appointmentDate, "787-8787",45 );
        controller.addAppointment(appointment);

        verify(service, times(1)).saveAppointment(appointment);
    }

    @DisplayName("Test for add list of appointments controller")
    @Test
    public void addAppointmentsTest(){
        Date appointmentDate1 = new Date(2021, 6, 12);
        Date appointmentDate2 = new Date(2021, 8, 2);

        Appointment appointment1 = new Appointment(2L, "paul" , "787-565-5666", appointmentDate1, "332-4342",45 );
        Appointment appointment2 = new Appointment(3L, "ricky" , "876-333-4663", appointmentDate2, "423-4321",33 );
        List<Appointment> appointmentList = Arrays.asList(appointment1,appointment2);
        controller.addAppointments(appointmentList);

        verify(service,times(1)).saveAppointments(appointmentList);
    }

    @DisplayName("Test for get appointment by id controller")
    @Test
    public void getAppointmentByIdTest(){
        Long id = 6L;
        controller.getAppointmentById(id);

        verify(service,times(1)).findAppointmentById(id);
    }

    @DisplayName("Test for get all appointment controller")
    @Test
    public void getAppointmentsTest(){
        controller.getAppointments();

        verify(service, times(1)).findAllAppointments();
    }

    @DisplayName("Test for get appointment by date range controller")
    @Test
    public void getAppointmentByDateRange(){
        Date dateFrom = new Date(2021, 1, 13);
        Date dateTo = new Date(2021, 5,4);

        controller.getAppointmentByDateRange(dateFrom, dateTo);

        verify(service, times(1)).getAllAppointmentByDateRange(dateFrom,dateTo);
    }

    @DisplayName("Test for delete appointment controller")
    @Test
    public void deleteTest(){
        Date appointmentDate1 = new Date(2021, 6, 12);

        Appointment appointment = new Appointment(2L, "paul" , "787-565-5666", appointmentDate1, "332-4342",45 );

        controller.delete(appointment);
        verify(service,times(1)).deleteAppointments(appointment);
    }

    @DisplayName("Test for delete appointment by id controller")
    @Test
    public void deleteByIdTest(){
        Long id= 3L;

        controller.deleteById(id);
        verify(service, times(1)).deleteAppointmentById(id);
    }

    @DisplayName("Test for update appointment controller")
    @Test
    public void updateTest(){
        Date appointmentDate1 = new Date(2021, 6, 12);

        Appointment appointment = new Appointment(2L, "paul" , "787-565-5666", appointmentDate1, "332-4342",45 );

        controller.update(appointment);
        verify(service, times(1)).updateAppointment(appointment);
    }
}
