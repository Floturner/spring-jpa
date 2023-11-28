package dev.floturner.springjpa;

import com.github.javafaker.Faker;
import dev.floturner.springjpa.books.Book;
import dev.floturner.springjpa.courses.Course;
import dev.floturner.springjpa.courses.CourseRepository;
import dev.floturner.springjpa.enrolments.Enrolment;
import dev.floturner.springjpa.enrolments.EnrolmentId;
import dev.floturner.springjpa.enrolments.EnrolmentRepository;
import dev.floturner.springjpa.students.Student;
import dev.floturner.springjpa.students.StudentRepository;
import dev.floturner.springjpa.student_id_cards.StudentIdCard;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner(
      StudentRepository studentRepository,
      CourseRepository courseRepository,
      EnrolmentRepository enrolmentRepository) {
    return args -> {
      Faker faker = new Faker();
      String firstName = faker.name().firstName();
      String lastName = faker.name().lastName();
      String email = String.format("%s.%s@gmail.com", firstName, lastName);
      Student student =
          new Student(firstName, lastName, email, faker.number().numberBetween(18, 40));

      student.addBook(new Book("Clean Code", LocalDateTime.now().minusMonths(4)));
      student.addBook(new Book("Java Master Class", LocalDateTime.now().minusYears(2)));
      student.addBook(new Book("Spring Boot 3 Master Class", LocalDateTime.now()));
      student.setStudentIdCard(new StudentIdCard("123456789", student));
      Student savedStudent = studentRepository.save(student);

      Course course1 = new Course("Computer Science", "IT");
      Course course2 = new Course("Java", "IT");
      Course course3 = new Course("Web Development", "IT");
      courseRepository
          .saveAll(List.of(course1, course2, course3))
          .forEach(
              course -> {
                Enrolment en =
                    enrolmentRepository.save(
                        new Enrolment(
                            new EnrolmentId(savedStudent.getId(), course.getId()),
                            savedStudent,
                            course,
                            LocalDateTime.now()));
                savedStudent.addEnrolment(en);
              });
      studentRepository.save(savedStudent);
    };
  }

  private static void generateRandomStudents(StudentRepository studentRepository) {
    Faker faker = new Faker();
    for (int i = 0; i < 40; i++) {
      String firstName = faker.name().firstName();
      String lastName = faker.name().lastName();
      String email = String.format("%s.%s@gmail.com", firstName, lastName);
      Student student =
          new Student(firstName, lastName, email, faker.number().numberBetween(18, 40));
      studentRepository.save(student);
    }
  }
}
