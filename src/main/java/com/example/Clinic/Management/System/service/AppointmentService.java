package com.example.Clinic.Management.System.service;

import com.example.Clinic.Management.System.model.Appointments;
import com.example.Clinic.Management.System.model.Status;
import com.example.Clinic.Management.System.model.Users;
import com.example.Clinic.Management.System.repository.AppointmentRepository;
import com.example.Clinic.Management.System.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointments createAppointment(Long patientId, Long doctorId, LocalDateTime appointmentDate) {
        Users doctor = usersRepository.findById(doctorId.intValue()).orElseThrow();
        Users patient = usersRepository.findById(patientId.intValue()).orElseThrow();

        boolean exists = appointmentRepository.existsByDoctorUserIdAndAppointmentDate(doctorId,appointmentDate);
        if(exists){
            throw new RuntimeException("The Doctor you are trying to book already has an appointment at this time");
        }

        Appointments appointments = new Appointments();

        appointments.setPatient(patient);
        appointments.setDoctor(doctor);
        appointments.setAppointmentDate(appointmentDate);
        appointments.setStatus(Status.PENDING);

        return  appointmentRepository.save(appointments);
    }
}
