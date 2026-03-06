package com.example.Clinic.Management.System.controller;

import com.example.Clinic.Management.System.model.Appointments;
import com.example.Clinic.Management.System.repository.AppointmentRepository;
import com.example.Clinic.Management.System.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @PostMapping("/appointment")
//    public Appointments createAppointment(Long patientId, Long doctorId,LocalDateTime appointmentDate){
//        return appointmentService.createAppointment(patientId,doctorId,appointmentDate);
//    }
    public ResponseEntity<String> createAppointment(@RequestBody Map<String, String> request) {

        Long doctorId = Long.parseLong(request.get("doctorId"));
        Long patientId = Long.parseLong(request.get("patientId"));
        LocalDateTime appointmentDate = LocalDateTime.parse(request.get("appointmentDate"));

        appointmentService.createAppointment(patientId, doctorId, appointmentDate);
        return ResponseEntity.ok("You have booked your appointment successfully");
    }
}
