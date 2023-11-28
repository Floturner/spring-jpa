package dev.floturner.springjpa.enrolments;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EnrolmentId implements Serializable {
  @Column(name = "student_id", nullable = false)
  private Long studentId;

  @Column(name = "course_id", nullable = false)
  private Long courseId;

  public EnrolmentId() {}

  public EnrolmentId(Long studentId, Long courseId) {
    this.studentId = studentId;
    this.courseId = courseId;
  }

  public Long getStudentId() {
    return studentId;
  }

  public void setStudentId(Long studentId) {
    this.studentId = studentId;
  }

  public Long getCourseId() {
    return courseId;
  }

  public void setCourseId(Long courseId) {
    this.courseId = courseId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EnrolmentId that = (EnrolmentId) o;
    return Objects.equals(studentId, that.studentId) && Objects.equals(courseId, that.courseId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentId, courseId);
  }

  @Override
  public String toString() {
    return "EnrolmentId{" + "studentId=" + studentId + ", courseId=" + courseId + '}';
  }
}
