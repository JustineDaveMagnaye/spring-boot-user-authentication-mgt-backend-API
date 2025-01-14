package com.rocs.user.repository.timeinout;

import com.rocs.user.domain.timerecord.TimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeInOutRepository extends JpaRepository<TimeRecord, String> {

    TimeRecord findByEmployee_EmployeeNumberAndCreatedAt(String employeeNumber, String createdAt);

    List<TimeRecord> getAllByEmployee_EmployeeNumber(String employeeNumber);

}
