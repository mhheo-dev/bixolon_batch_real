package com.bixpos.batch.business.step.chunk.writer;

import com.bixpos.batch.business.domain.Table;
import com.bixpos.batch.business.mapper.DemoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@StepScope
@Component
@RequiredArgsConstructor
@Slf4j
public class DemoItemWriter implements ItemWriter<Table.DemoTable> {
    private final DemoMapper demoMapper;

    @Override
    public void write(List<? extends Table.DemoTable> items) throws Exception {
        for(Table.DemoTable item : items) {
            log.info("data insert:" + item);
            demoMapper.insertDemo(item);
        }
    }

}
