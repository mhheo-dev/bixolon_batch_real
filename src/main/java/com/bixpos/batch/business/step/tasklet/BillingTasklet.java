package com.bixpos.batch.business.step.tasklet;

import com.bixpos.batch.business.mapper.BillingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class BillingTasklet implements Tasklet {

    private final BillingMapper calculateUseFeeMapper;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("Starting BillingTasklet execution");
        try {
            int updatedCount = calculateUseFeeMapper.updateBillingStatus();
            log.info("BillingTasklet completed. Updated {} records", updatedCount);
            contribution.incrementWriteCount(updatedCount);
        } catch (Exception e) {
            log.error("Error occurred during BillingTasklet execution", e);
            throw e;
        }
        return RepeatStatus.FINISHED;
    }
}