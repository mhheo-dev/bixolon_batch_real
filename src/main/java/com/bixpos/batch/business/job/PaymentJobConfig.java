package com.bixpos.batch.business.job;

import com.bixpos.batch.business.step.chunk.payment.PaymentItemProcessor;
import com.bixpos.batch.business.step.chunk.payment.PaymentItemReader;
import com.bixpos.batch.business.step.chunk.payment.PaymentItemWriter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PaymentJobConfig {

    @Bean
    public ItemReader<Object> reader() {
        return new PaymentItemReader();
    }

    @Bean
    public ItemProcessor<Object, Object> processor() {
        return new PaymentItemProcessor();
    }

    @Bean
    public ItemWriter<Object> writer() {
        return new PaymentItemWriter();
    }
}