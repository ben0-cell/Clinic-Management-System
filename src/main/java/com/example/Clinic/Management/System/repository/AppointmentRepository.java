package com.example.Clinic.Management.System.repository;

import com.example.Clinic.Management.System.model.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointments, Integer> {
    boolean existsByDoctorUserIdAndAppointmentDate(Long doctorId, LocalDateTime appointmentDate);
}

