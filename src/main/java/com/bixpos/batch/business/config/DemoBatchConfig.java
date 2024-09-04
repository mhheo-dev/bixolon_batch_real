package com.bixpos.batch.business.config;

import com.bixpos.batch.business.domain.Reference;
import com.bixpos.batch.business.domain.Table;
import com.bixpos.batch.business.step.chunk.DemoItemProcessor;
import com.bixpos.batch.business.step.chunk.DemoItemReader;
import com.bixpos.batch.business.step.chunk.DemoItemWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class DemoBatchConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DemoItemReader reader;
    private final DemoItemProcessor processor;
    private final DemoItemWriter writer;

    @Bean
    public Job demoJob() {
        return jobBuilderFactory.get("demoJob")
                .start(demoStep())
                .build();
    }

    @Bean
    public Step demoStep() {
        return stepBuilderFactory.get("demoStep")
                .<Reference.DemoReference, Table.DemoTable>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
