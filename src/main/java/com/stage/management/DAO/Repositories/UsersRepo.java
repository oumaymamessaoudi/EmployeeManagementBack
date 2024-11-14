package com.stage.management.DAO.Repositories;


import com.stage.management.DAO.Entities.ERole;
import com.stage.management.DAO.Entities.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepo extends JpaRepository<OurUsers, Integer> {

   Optional<OurUsers> findByEmail(String email);
   List<OurUsers> findByRole(ERole role);
   List<OurUsers> findAllByRole(ERole role);

}