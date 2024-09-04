package com.bixpos.batch.business.config;

import com.bixpos.batch.business.step.tasklet.InitializationTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bixpos.batch.business.job.DiscountJobConfig;
import com.bixpos.batch.business.job.PaymentJobConfig;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

    private final DiscountJobConfig discountJobConfig;
    private final PaymentJobConfig paymentJobConfig;
    private final InitializationTasklet initializationTasklet;

    @Bean
    public Job initializationJob(){
        return new JobBuilder("initialization")
                .start(initializationStep())
                .build();
    }

    @Bean
    public Step initializationStep() {
        return new StepBuilder("initializationStep")
                .tasklet(initializationTasklet)
                .build();
    }


    @Bean
    public Job discountJob() {
        return new JobBuilder("discountJob")
                .start(discountStep())
                .build();
    }

    @Bean
    public Step discountStep() {
        return new StepBuilder("discountStep")
                .<Object, Object>chunk(10)
                .reader(discountJobConfig.reader())
                .processor(discountJobConfig.processor())
                .writer(discountJobConfig.writer())
                .build();
    }

    @Bean
    public Job paymentJob() {
        return new JobBuilder("paymentJob")
                .start(paymentStep())
                .build();
    }

    @Bean
    public Step paymentStep() {
        return new StepBuilder("paymentStep")
                .<Object, Object>chunk(10)
                .reader(paymentJobConfig.reader())
                .processor(paymentJobConfig.processor())
                .writer(paymentJobConfig.writer())
                .build();
    }
}