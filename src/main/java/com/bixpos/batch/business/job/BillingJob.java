package com.bixpos.batch.business.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class BillingJob extends QuartzJobBean {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job billingJob;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(billingJob, jobParameters);
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}
