package com.example.Clinic.Management.System.service;

import com.example.Clinic.Management.System.model.Appointments;
import com.example.Clinic.Management.System.model.Status;
import com.example.Clinic.Management.System.model.Users;
import com.example.Clinic.Management.System.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.Clinic.Management.System.repository.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Optional<Users> getUserById(int id) {
        return usersRepository.findById(id);
    }

    public Users registerUserByAdmin(Users users) {
        if(usersRepository.findByUsername(users.getUsername()).isPresent()){
            throw new RuntimeException("Username already exists");
        }

        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return usersRepository.save(users);
    }

    public void updateUsers(Users users){
        if(usersRepository.findByUsername(users.getUsername()).isPresent()){
            users.setPassword(passwordEncoder.encode(users.getPassword()));
            usersRepository.save(users);
        }
        else {
            throw new RuntimeException("Username already exists");
        }
        }

    public void deleteUsers(int userId) {
        usersRepository.deleteById(userId);
    }


    public List<Appointments> getAllAppointments() {
        return appointmentRepository.findAll(Sort.by("appointmentDate").ascending());
    }

    public Appointments confirmAppointments(Integer appointmentId) {
        Appointments appointments = appointmentRepository.findById(Math.toIntExact(appointmentId))
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointments.setStatus(Status.CONFIRMED);
        return appointmentRepository.save(appointments);
    }

    public Appointments cancelAppointments(Integer appointmentId) {
        Appointments appointments = appointmentRepository.findById(Math.toIntExact(appointmentId))
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointments.setStatus(Status.CANCELLED);
        return appointmentRepository.save(appointments);
    }
}
