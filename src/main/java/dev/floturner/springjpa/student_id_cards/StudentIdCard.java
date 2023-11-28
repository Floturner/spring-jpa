package dev.floturner.springjpa.student_id_cards;

import dev.floturner.springjpa.students.Student;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "StudentIdCard")
@Table(
    name = "student_id_card",
    uniqueConstraints = {
      @UniqueConstraint(name = "student_id_card_number_unique", columnNames = "card_number")
    })
public class StudentIdCard {
  @Id
  @SequenceGenerator(
      name = "student_id_card_sequence",
      sequenceName = "student_id_card_sequence",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_card_sequence")
  @Column(name = "id", updatable = false)
  private Long id;

  @Column(name = "card_number", nullable = false, length = 15)
  private String cardNumber;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(
      name = "student_id",
      referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "student_id_fkey"))
  private Student student;

  public StudentIdCard() {}

  public StudentIdCard(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public StudentIdCard(String cardNumber, Student student) {
    this.cardNumber = cardNumber;
    this.student = student;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StudentIdCard that = (StudentIdCard) o;
    return Objects.equals(id, that.id) && Objects.equals(cardNumber, that.cardNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, cardNumber);
  }

  @Override
  public String toString() {
    return "StudentIdCard{" + "id=" + id + ", cardNumber='" + cardNumber + '\'' + '}';
  }
}
