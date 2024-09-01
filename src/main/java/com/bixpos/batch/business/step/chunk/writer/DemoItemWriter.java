package com.bixpos.batch.business.step.chunk.writer;

import com.bixpos.batch.business.domain.Table;
import com.bixpos.batch.business.mapper.DemoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@StepScope
@Component
@RequiredArgsConstructor
public class DemoItemWriter implements ItemWriter<Table.DemoTable> {
    private final DemoMapper demoMapper;

    @Override
    public void write(List<? extends Table.DemoTable> items) throws Exception {
        for(Table.DemoTable item : items) {
            demoMapper.insertDemoReference(item);
        }
    }

}
