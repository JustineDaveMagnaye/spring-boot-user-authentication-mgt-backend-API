package com.rocs.user.service.timeinout;

import com.rocs.user.domain.secretphrase.SecretPhrase;
import com.rocs.user.domain.timerecord.TimeRecord;
import com.rocs.user.exception.domain.DateExistsException;

import java.util.List;

public interface TimeInOutService {
    TimeRecord addTimeIn(String employeeNumber) throws DateExistsException;
    TimeRecord addTimeOut(String employeeNumber) throws DateExistsException;
    List<TimeRecord> getUserLogs(String employeeNumber) throws DateExistsException;

}
