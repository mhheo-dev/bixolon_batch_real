package com.bixpos.batch.business.step.tasklet;

import com.bixpos.batch.business.mapper.BillingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@StepScope
@Component
public class InitializationTasklet implements Tasklet {

    private final BillingMapper billingMapper;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("Starting InitializationTasklet execution");
        try {
            int updatedCount = billingMapper.updateBillingStatus();
            log.info("InitializationTasklet completed. Updated {} records", updatedCount);
            contribution.incrementWriteCount(updatedCount);
        } catch (Exception e) {
            log.error("Error occurred during InitializationTasklet execution", e);
            throw e;
        }
        return RepeatStatus.FINISHED;
    }
}
