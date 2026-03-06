package com.example.Clinic.Management.System.repository;

import com.example.Clinic.Management.System.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {

    Optional<Users> findByUsername(String username);

}
