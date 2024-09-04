package com.bixpos.batch.business.job;

import com.bixpos.batch.business.step.chunk.discount.DiscountItemProcessor;
import com.bixpos.batch.business.step.chunk.discount.DiscountItemReader;
import com.bixpos.batch.business.step.chunk.discount.DiscountItemWriter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscountJobConfig {

    @Bean
    public ItemReader<Object> reader() {
        return new DiscountItemReader();
    }

    @Bean
    public ItemProcessor<Object, Object> processor() {
        return new DiscountItemProcessor();
    }

    @Bean
    public ItemWriter<Object> writer() {
        return new DiscountItemWriter();
    }
}
