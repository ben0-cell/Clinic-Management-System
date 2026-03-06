package com.example.Clinic.Management.System.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Appointments {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int appointmentId;
    private LocalDateTime appointmentDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "patient_user_id")
    private Users patient;

    @ManyToOne
    @JoinColumn(name = "doctor_user_id")
    private Users doctor;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public String getPatient() {
        return patient.getUsername();
    }

    public String getDoctor() {
        return doctor.getUsername();
    }
}
