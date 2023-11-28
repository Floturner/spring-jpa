package dev.floturner.springjpa.students;

import dev.floturner.springjpa.books.Book;
import dev.floturner.springjpa.enrolments.Enrolment;
import dev.floturner.springjpa.student_id_cards.StudentIdCard;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Student")
@Table(
    name = "student",
    uniqueConstraints = {@UniqueConstraint(name = "student_email_unique", columnNames = "email")})
public class Student {
  @Id
  @SequenceGenerator(
      name = "student_sequence",
      sequenceName = "student_sequence",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
  @Column(name = "id", updatable = false)
  private Long id;

  @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
  private String firstName;

  @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
  private String lastName;

  @Column(name = "email", nullable = false, columnDefinition = "TEXT")
  private String email;

  @Column(name = "age", nullable = false)
  private Integer age;

  @OneToOne(
      mappedBy = "student",
      orphanRemoval = true,
      cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  private StudentIdCard studentIdCard;

  @OneToMany(
      mappedBy = "student",
      orphanRemoval = true,
      cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  private final List<Book> books = new ArrayList<>();

  @OneToMany(
      mappedBy = "student",
      cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  private final List<Enrolment> enrolments = new ArrayList<>();

  public Student() {}

  public Student(String firstName, String lastName, String email, Integer age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.age = age;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public StudentIdCard getStudentIdCard() {
    return studentIdCard;
  }

  public void setStudentIdCard(StudentIdCard studentIdCard) {
    this.studentIdCard = studentIdCard;
  }

  public List<Book> getBooks() {
    return books;
  }

  public List<Enrolment> getEnrolments() {
    return enrolments;
  }

  public void addBook(Book book) {
    if (!this.books.contains(book)) {
      this.books.add(book);
      book.setStudent(this);
    }
  }

  public void removeBook(Book book) {
    if (this.books.contains(book)) {
      this.books.remove(book);
      book.setStudent(null);
    }
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
    Student student = (Student) o;
    return Objects.equals(id, student.id)
        && Objects.equals(firstName, student.firstName)
        && Objects.equals(lastName, student.lastName)
        && Objects.equals(email, student.email)
        && Objects.equals(age, student.age);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, email, age);
  }

  @Override
  public String toString() {
    return "Student{"
        + "id="
        + id
        + ", firstName='"
        + firstName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", email='"
        + email
        + '\''
        + ", age="
        + age
        + '}';
  }
}
