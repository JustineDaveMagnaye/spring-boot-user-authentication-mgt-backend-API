package com.rocs.user.domain.secretphrase;

import com.rocs.user.domain.employee.Employee;
import com.rocs.user.domain.person.Person;
import com.rocs.user.domain.user.User;
import com.rocs.user.utils.converter.StringListConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecretPhrase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String secretPhrase;

    @OneToOne
    @JoinColumn(name = "employee_number", referencedColumnName = "employeeNumber")
    private Employee employee;
}
