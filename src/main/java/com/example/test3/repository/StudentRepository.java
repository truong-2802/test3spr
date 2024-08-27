package com.example.test3.repository;

import com.example.test3.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s WHERE " +
            "(COALESCE(:name, '') = '' OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(COALESCE(:minScore, -1) = -1 OR s.score >= :minScore) AND " +
            "(COALESCE(:maxScore, -1) = -1 OR s.score <= :maxScore) AND " +
            "(COALESCE(:minCredit, -1) = -1 OR s.credit >= :minCredit) AND " +
            "(COALESCE(:maxCredit, -1) = -1 OR s.credit <= :maxCredit) AND " +
            "(COALESCE(:grade, '') = '' OR s.grade = :grade) AND " +
            "(COALESCE(:gender, '') = '' OR s.gender = :gender)")
    List<Student> findStudents(
            @Param("name") String name,
            @Param("minScore") Integer minScore,
            @Param("maxScore") Integer maxScore,
            @Param("minCredit") Float minCredit,
            @Param("maxCredit") Float maxCredit,
            @Param("grade") Character grade,
            @Param("gender") Boolean gender);
}
