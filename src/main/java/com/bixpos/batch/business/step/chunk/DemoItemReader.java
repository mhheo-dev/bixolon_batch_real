package com.bixpos.batch.business.step.chunk;

import com.bixpos.batch.business.domain.Reference.DemoReference;

import com.bixpos.batch.business.mapper.DemoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@StepScope
@Component
@RequiredArgsConstructor
public class DemoItemReader implements ItemReader<DemoReference> {

    private final DemoMapper demoMapper;
    private Iterator<DemoReference> demoReferenceIterator;

    @Override
    public DemoReference read() {
        if (demoReferenceIterator == null) {
            List<DemoReference> demoReferences = demoMapper.getDemoReference();
            demoReferenceIterator = demoReferences.iterator();
        }

        return demoReferenceIterator.hasNext() ? demoReferenceIterator.next() : null;
    }
}