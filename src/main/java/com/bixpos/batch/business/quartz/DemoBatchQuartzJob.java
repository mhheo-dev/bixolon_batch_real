//package com.bixpos.batch.business.quartz;
//
//import lombok.RequiredArgsConstructor;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.scheduling.quartz.QuartzJobBean;
//
//@RequiredArgsConstructor
//public class DemoBatchQuartzJob extends QuartzJobBean {
//
//    private final JobLauncher jobLauncher;
//    private final Job demoJob;  // This should match the name of your @Bean method in DemoBatchConfig
//
//    @Override
//    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
//        try {
//            JobParameters jobParameters = new JobParametersBuilder()
//                    .addLong("time", System.currentTimeMillis())
//                    .toJobParameters();
//            jobLauncher.run(demoJob, jobParameters);
//        } catch (Exception e) {
//            throw new JobExecutionException(e);
//        }
//    }
//}