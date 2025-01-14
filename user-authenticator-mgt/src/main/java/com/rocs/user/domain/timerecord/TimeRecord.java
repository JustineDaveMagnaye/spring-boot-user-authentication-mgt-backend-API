package com.rocs.user.domain.timerecord;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rocs.user.domain.employee.Employee;
import com.rocs.user.domain.secretphrase.SecretPhrase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_number", referencedColumnName = "employeeNumber")
    private Employee employee;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date timeIn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeOut;

    private long totalHours;

    @Column(length = 255, nullable = false)
    private String createdAt;

    @Column(length = 255, nullable = false)
    private String type;
}
