package com.bixpos.batch.business.step.chunk.processor;

import com.bixpos.batch.business.domain.Reference;
import com.bixpos.batch.business.domain.Table;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
@StepScope
@Component
public class DemoItemProcessor implements ItemProcessor<Reference.DemoReference, Table.DemoTable> {

    @Override
    public Table.DemoTable process(Reference.DemoReference item) {
        Table.DemoTable demoTable = new Table.DemoTable();
        demoTable.setDemoColumn1(item.getDemoColumn1());
        demoTable.setDemoColumn2(item.getDemoColumn2());
        demoTable.setDemoValue(item.getDemoValue()+1);

        log.info("Processing: {} -> {}", item, demoTable);
        return null;
    }
}
