package com.durgesh94.crudDemoApp.repository;

import com.durgesh94.crudDemoApp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByIdAndDeletedFalse(Long id);
    List<Student> findByDeletedIsFalse();
    Optional<Student> findByRollNoAndDeletedFalse(int id);

}
