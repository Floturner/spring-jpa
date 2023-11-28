package dev.floturner.springjpa.courses;

import dev.floturner.springjpa.enrolments.Enrolment;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Course")
@Table(name = "course")
public class Course {
  @Id
  @SequenceGenerator(name = "course_sequence", sequenceName = "course_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
  @Column(name = "id", updatable = false)
  private Long id;

  @Column(name = "name", nullable = false, columnDefinition = "TEXT")
  private String name;

  @Column(name = "department", nullable = false, columnDefinition = "TEXT")
  private String department;

  @OneToMany(
      mappedBy = "course",
      cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  private final List<Enrolment> enrolments = new ArrayList<>();

  public Course() {}

  public Course(String name, String department) {
    this.name = name;
    this.department = department;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public List<Enrolment> getEnrolments() {
    return enrolments;
  }

  public void addEnrolment(Enrolment enrolment) {
    if (!enrolments.contains(enrolment)) enrolments.add(enrolment);
  }

  public void removeEnrolment(Enrolment enrolment) {
    enrolments.remove(enrolment);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Course course = (Course) o;
    return Objects.equals(id, course.id)
        && Objects.equals(name, course.name)
        && Objects.equals(department, course.department);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, department);
  }

  @Override
  public String toString() {
    return "Course{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", department='"
        + department
        + '\''
        + '}';
  }
}
