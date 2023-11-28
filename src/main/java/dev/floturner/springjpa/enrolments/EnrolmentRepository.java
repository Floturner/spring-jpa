package dev.floturner.springjpa.enrolments;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolmentRepository extends CrudRepository<Enrolment, EnrolmentId> {}
