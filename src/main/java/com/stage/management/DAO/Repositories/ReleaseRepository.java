package com.stage.management.DAO.Repositories;

import com.stage.management.DAO.Entities.Release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Integer> {
}
