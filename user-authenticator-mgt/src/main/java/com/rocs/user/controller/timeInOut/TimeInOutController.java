package com.rocs.user.controller.timeInOut;

import com.rocs.user.domain.secretphrase.SecretPhrase;
import com.rocs.user.domain.timerecord.TimeRecord;
import com.rocs.user.exception.domain.DateExistsException;
import com.rocs.user.service.timeinout.TimeInOutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/time-record")
public class TimeInOutController {
    TimeInOutService timeInOutService;

    public TimeInOutController(TimeInOutService timeInOutService) {
        this.timeInOutService = timeInOutService;
    }

    @PostMapping("/addTimeIn")
    public ResponseEntity<String> addTimeIn(@RequestBody TimeRecord timeRecord) throws DateExistsException {
        try{
            TimeRecord timeRecord1 = timeInOutService.addTimeIn("CT21-0143");
            return new ResponseEntity<>("hi", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addTimeOut")
    public ResponseEntity<String> addTimeOut(@RequestBody TimeRecord timeRecord) throws DateExistsException {
        try{
            TimeRecord timeRecord1 = timeInOutService.addTimeOut("CT21-0143");
            return new ResponseEntity<>("hi", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getUserLogs")
    public ResponseEntity<List<TimeRecord>> getUserLogs(@RequestBody TimeRecord timeRecord) throws DateExistsException {
        return new ResponseEntity<>(timeInOutService.getUserLogs("CT21-0143"), HttpStatus.OK);
    }
}
