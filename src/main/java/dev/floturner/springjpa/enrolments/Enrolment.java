package dev.floturner.springjpa.enrolments;

import dev.floturner.springjpa.courses.Course;
import dev.floturner.springjpa.students.Student;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "Enrolment")
@Table(name = "enrolment")
public class Enrolment {

  @EmbeddedId private EnrolmentId id;

  @ManyToOne
  @MapsId("studentId")
  @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "enrolment_student_id_fkey"))
  private Student student;

  @ManyToOne
  @MapsId("courseId")
  @JoinColumn(name = "course_id", foreignKey = @ForeignKey(name = "enrolment_course_id_fkey"))
  private Course course;

  @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
  private LocalDateTime createdAt;

  public Enrolment() {}

  public Enrolment(Student student, Course course, LocalDateTime createdAt) {
    this.student = student;
    this.course = course;
    this.createdAt = createdAt;
  }

  public Enrolment(EnrolmentId id, Student student, Course course, LocalDateTime createdAt) {
    this.id = id;
    this.student = student;
    this.course = course;
    this.createdAt = createdAt;
  }

  public EnrolmentId getId() {
    return id;
  }

  public void setId(EnrolmentId id) {
    this.id = id;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Enrolment enrolment = (Enrolment) o;
    return Objects.equals(id, enrolment.id)
        && Objects.equals(student, enrolment.student)
        && Objects.equals(course, enrolment.course)
        && Objects.equals(createdAt, enrolment.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, student, course, createdAt);
  }

  @Override
  public String toString() {
    return "Enrolment{"
        + "id="
        + id
        + ", student="
        + student
        + ", course="
        + course
        + ", createdAt="
        + createdAt
        + '}';
  }
}
