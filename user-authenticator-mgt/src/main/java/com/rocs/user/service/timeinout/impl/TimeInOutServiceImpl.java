package com.rocs.user.service.timeinout.impl;

import com.rocs.user.domain.employee.Employee;
import com.rocs.user.domain.secretphrase.SecretPhrase;
import com.rocs.user.domain.timerecord.TimeRecord;
import com.rocs.user.exception.domain.DateExistsException;
import com.rocs.user.repository.employee.EmployeeRepository;
import com.rocs.user.repository.timeinout.TimeInOutRepository;
import com.rocs.user.service.timeinout.TimeInOutService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TimeInOutServiceImpl implements TimeInOutService {
    private TimeInOutRepository timeInOutRepository;
    private EmployeeRepository employeeRepository;

    public TimeInOutServiceImpl(TimeInOutRepository timeInOutRepository, EmployeeRepository employeeRepository) {
        this.timeInOutRepository = timeInOutRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public TimeRecord addTimeIn(String employeeNumber) throws DateExistsException {
        Employee findEmployee = employeeRepository.findByEmployeeNumber(employeeNumber);
        if(findEmployee != null){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date currentDate = new Date();
            String dateString = format.format(currentDate);
            if(timeInOutRepository.findByEmployee_EmployeeNumberAndCreatedAt(employeeNumber,dateString) != null){
                throw new  DateExistsException("You have already timed in today.");
            } else {
                TimeRecord newRecord = new TimeRecord();
                newRecord.setEmployee(findEmployee);
                newRecord.setCreatedAt(dateString);
                newRecord.setTimeIn(currentDate);
                newRecord.setType("Online");
                return timeInOutRepository.save(newRecord);
            }
        }
        return null;
    }

    @Override
    public TimeRecord addTimeOut(String employeeNumber) throws DateExistsException {
        Employee findEmployee = employeeRepository.findByEmployeeNumber(employeeNumber);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String dateString = format.format(currentDate);

        if (findEmployee != null) {
            TimeRecord data = timeInOutRepository.findByEmployee_EmployeeNumberAndCreatedAt(employeeNumber, dateString);
            if(data == null){
                throw new DateExistsException("You haven't timed in today.");
            }
            if (data != null && data.getTimeIn() != null && data.getTimeOut() == null) {
                data.setTimeOut(currentDate);
                long durationMillis = currentDate.getTime() - data.getTimeIn().getTime();
                data.setTotalHours((long) (durationMillis / 3600000.0));
                if(data.getType().equals("Onsite")){
                    data.setType("Onsite");
                } else {
                    data.setType("Online");
                }
                return timeInOutRepository.save(data);
            } else {
                assert data != null;
                if (data.getTimeOut() != null) {
                    throw new DateExistsException("You have already timed out today.");
                }
            }
        }
        throw new DateExistsException("Employee not found.");
    }

    @Override
    public List<TimeRecord> getUserLogs(String employeeNumber) throws DateExistsException {
        Employee findEmployee = employeeRepository.findByEmployeeNumber(employeeNumber);
        System.out.println(employeeNumber);
        if(findEmployee != null){
        return timeInOutRepository.getAllByEmployee_EmployeeNumber(employeeNumber);
        }
        throw new DateExistsException("Employee not found.");
    }
}
