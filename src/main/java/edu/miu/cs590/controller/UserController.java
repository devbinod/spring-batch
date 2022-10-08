package edu.miu.cs590.controller;

import edu.miu.cs590.entity.User;
import edu.miu.cs590.repository.UserRepository;
import edu.miu.cs590.service.StudentService;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@Secured("ROLE_ADMIN")
public class UserController {

    @Autowired
    private StudentService studentService;


    @GetMapping("/run-batch")
    public String runBatch() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        studentService.run();
        return "successfully run job";
    }


    @GetMapping("/count-record")
    public long studentCount() {
        return studentService.count();
    }
}
