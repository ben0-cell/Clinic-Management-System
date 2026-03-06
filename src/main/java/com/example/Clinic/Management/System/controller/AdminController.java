package com.example.Clinic.Management.System.controller;

import com.example.Clinic.Management.System.model.Appointments;
import com.example.Clinic.Management.System.model.Users;
import com.example.Clinic.Management.System.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.Clinic.Management.System.service.AdminService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/dashboard")
    public String home(){
        return "Welcome Admin";
    }
     @GetMapping("/users")
    public List<Users> getAllUsers(){
         return adminService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public Optional<Users> getUserById(@PathVariable int id){
      return adminService.getUserById(id);
    }

    @PostMapping("/register")
    public Users registerUserByAdmin(@RequestBody Users users){
        return adminService.registerUserByAdmin(users);
    }

    @PutMapping("/update_user")
    public void updateUsers(@RequestBody Users users){
        adminService.updateUsers(users);
    }
    @DeleteMapping("/delete_user/{userId}")
    public void deleteUsers(@PathVariable int userId){
        adminService.deleteUsers(userId);
    }

    @GetMapping("/view_appointments")
    public List <Appointments>getAllAppointments(){
        return adminService.getAllAppointments();
    }

    @PutMapping("/appointment/confirm{id}")
    public Appointments confirmAppointments(@PathVariable Integer appointmentId){
        return adminService.confirmAppointments(appointmentId);
    }

    @PutMapping("/appointment/cancel{id}")
    public Appointments cancelAppointments(@PathVariable Integer appointmentId){
        return adminService.cancelAppointments(appointmentId);
    }

}
