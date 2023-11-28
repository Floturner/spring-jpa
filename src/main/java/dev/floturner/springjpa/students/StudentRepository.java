package dev.floturner.springjpa.students;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
  @Query("SELECT s FROM Student s WHERE s.email = ?1")
  Optional<Student> findByEmail(String email);

  @Query("SELECT s FROM Student s WHERE UPPER(s.firstName) = UPPER(?1) AND s.age = ?2")
  List<Student> findAllByFirstNameEqualsIgnoreCaseAndAgeEquals(String firstName, Integer age);

  @Query("SELECT s FROM Student s WHERE UPPER(s.firstName) = UPPER(?1) AND s.age >= ?2")
  List<Student> findAllByFirstNameEqualsIgnoreCaseAndAgeIsGreaterThanEqual(
      String firstName, Integer age);

  @Query(
      value = "SELECT s FROM Student s WHERE UPPER(s.firstName) = UPPER(?1) AND s.age >= ?2",
      nativeQuery = true)
  List<Student> selectByFirstNameEqualsIgnoreCaseAndAgeIsGreaterThanEqual(
      String firstName, Integer age);

  @Modifying
  @Transactional
  @Query("DELETE FROM Student s WHERE s.id = :id")
  int deleteStudentById(@Param("id") Long id);
}
