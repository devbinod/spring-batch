package edu.miu.cs590.service;

import edu.miu.cs590.repository.StudentRepository;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;


    @Autowired
    private StudentRepository studentRepository;

    public StudentServiceImpl(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @Override
    public void run() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobExecution jobExecution = jobLauncher.run(job, new JobParameters());

    }

    @Override
    public long count() {
        return studentRepository.count();
    }
}
