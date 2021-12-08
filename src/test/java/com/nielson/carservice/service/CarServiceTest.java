package com.nielson.carservice.service;

import com.nielson.carservice.entity.Appointment;
import com.nielson.carservice.repository.CarServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class CarServiceTest {

    public CarService service;

    @Mock
    private CarServiceRepository repository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(CarServiceTest.this);
        service = new CarService(repository);
    }

    @Test
    @DisplayName("Test for adding new Appointment")
    public void saveAppointmentTest(){
        Date addpointmentDate = new Date(2021, 11, 17);
      Appointment appointment = new Appointment(2L, "paul" , "787-565-5666", addpointmentDate, "787-8787",45 );
        when(repository.save(appointment)).thenReturn(appointment);

        assertEquals(appointment, service.saveAppointment(appointment));
    }

    @Test
    @DisplayName("Test for new list of Appointments")
    public void saveAppointmentsTest(){
        Date addpointmentDate1 = new Date(2021, 11, 17);
        Date addpointmentDate2 = new Date(2021, 8, 6);
        Appointment appointment1 = new Appointment(2L, "Addison", "545-234-1909", addpointmentDate1, "898-5656", 22);
        Appointment appointment2 = new Appointment(3L, "Laura", "787-565-5666", addpointmentDate2, "787-8787", 175);
        List<Appointment> appointmentList = Arrays.asList(appointment1, appointment2);

        when(repository.saveAll(appointmentList)).thenReturn(appointmentList);

        assertEquals(appointmentList, service.saveAppointments(appointmentList));
    }

    @Test
    @DisplayName("Test for finding appointment by Id")
    public void findAppointmentByIdTest(){
        Long id = 6L;
        Date appointmentDate = new Date(2021, 11, 17);
        Appointment apt = new Appointment(6L, "Addison", "545-234-1909",appointmentDate, "898-5656", 22);
        when(repository.findById(id)).thenReturn(Optional.of(apt));

        Appointment actualAppointment = service.findAppointmentById(id);
        verify(repository, times(1)).findById(id);
        assertEquals(6L, actualAppointment.getId());
        assertEquals("Addison", actualAppointment.getCarOwnerName());
        assertEquals("545-234-1909", actualAppointment.getPhoneNumber());
        assertEquals(appointmentDate, actualAppointment.getAppointmentDate());
        assertEquals("898-5656" , actualAppointment.getCarRegNumber());
        assertEquals(22, actualAppointment.getServicePrice());
    }

    @Test
    @DisplayName("Test for finding All Appointment")
    public void findAllAppointmentsTest(){
        Date appointmentDate1 = new Date(2021, 11, 17);
        Date appointmentDate2 = new Date(2021, 9, 01);
        Appointment apt1 = new Appointment(6L, "Addison", "545-234-1909",appointmentDate1, "898-5656", 22);
        Appointment apt2 = new Appointment(7L, "raj", "787-536-8273",appointmentDate2, "556-7646", 678);
        List<Appointment> appointmentList =Arrays.asList(apt1, apt2);

        when(repository.findAll()).thenReturn(appointmentList);


        List<Appointment> appointments = service.findAllAppointments();
        verify(repository, times(1)).findAll();
        assertEquals(2, appointments.size());
        assertEquals(6L,appointments.get(0).getId());
        assertEquals("Addison",appointments.get(0).getCarOwnerName());
        assertEquals("545-234-1909",appointments.get(0).getPhoneNumber());
        assertEquals(appointmentDate1,appointments.get(0).getAppointmentDate());
        assertEquals("898-5656",appointments.get(0).getCarRegNumber());
        assertEquals(22,appointments.get(0).getServicePrice());
    }

    @Test
    @DisplayName("Test to find All Appointment By DateRange")
    public void GetAllAppointmentByDateRangeTest(){
        Date dateFrom = new Date(2021, 1, 7);
        Date dateTo = new Date(2021,4,9) ;

        Date appointmentDate1 = new Date(2021, 2, 16);
        Date appointmentDate2 = new Date(2021, 6, 1);
        Date appointmentDate3 = new Date(2021, 4, 23);
        Appointment apt1 = new Appointment(6L, "Addison", "545-234-1909",appointmentDate1, "898-5656", 156);
        Appointment apt2 = new Appointment(7L, "raj", "787-536-8273",appointmentDate2, "556-7646", 22);
        Appointment apt3 = new Appointment(8L, "Peterson", "788-345-4256",appointmentDate3, "556-7646", 57);

        List<Appointment> appointmentList =Arrays.asList(apt1, apt2, apt3);

        when(repository.findAllByAppointmentDateBetween(dateFrom, dateTo)).thenReturn(appointmentList);
        List<Appointment> actualAppointments = service.getAllAppointmentByDateRange(dateFrom, dateTo);
        verify(repository, times(1)).findAllByAppointmentDateBetween(dateFrom, dateTo);
        assertEquals(22, actualAppointments.get(0).getServicePrice());
    }

    @Test
    @DisplayName("Test to delete appointment")
    public void deleteAppointmentsTest(){
        Date appointmentDate = new Date(2021, 11, 17);
        Appointment appointment = new Appointment(6L, "Addison", "545-234-1909",appointmentDate, "898-5656", 22);

        service.deleteAppointments(appointment);
        verify(repository, times(1)).delete(appointment);
    }

    @Test
    @DisplayName("Test to delete Appointment By Id")
    public void deleteAppointmentByIdTest(){
        Long id =3L;

        service.deleteAppointmentById(id);
        verify(repository,times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Test for update")
    public void updateAppointmentTest(){
        Date appointmentDate = new Date(2021, 11, 17);
        Appointment expectedAppointment = new Appointment(6L, "Addison", "545-234-1909",appointmentDate, "898-5656", 22);
        Appointment update = new Appointment(6L, "Richardson", "234-545-2011",appointmentDate, "231-4332", 76);

        when(repository.findById(anyLong())).thenReturn(Optional.of(expectedAppointment));
        when(repository.save(any())).thenReturn(update);

       Appointment actualAppointment = service.updateAppointment(update);
       verify(repository,times(1)).findById(anyLong());
       verify(repository,times(1)).save(actualAppointment);

       assertEquals(6L,actualAppointment.getId());
       assertEquals("Richardson", actualAppointment.getCarOwnerName());
       assertEquals("234-545-2011",actualAppointment.getPhoneNumber());
       assertEquals(appointmentDate,actualAppointment.getAppointmentDate());
       assertEquals("231-4332", actualAppointment.getCarRegNumber());
       assertEquals(76,actualAppointment.getServicePrice());

    }
}
