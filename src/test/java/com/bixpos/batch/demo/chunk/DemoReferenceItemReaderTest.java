package com.bixpos.batch.demo.chunk;

import com.bixpos.batch.business.domain.Reference;
import com.bixpos.batch.business.step.chunk.reader.DemoItemReader;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.StepScopeTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("local")
public class DemoReferenceItemReaderTest {

    @Autowired
    private DemoItemReader reader;

    @Test
    public void testRead() throws Exception {
        StepExecution stepExecution = MetaDataInstanceFactory.createStepExecution();

        int count = StepScopeTestUtils.doInStepScope(stepExecution, () -> {
            Reference.DemoReference item;
            int itemCount = 0;
            while ((item = reader.read()) != null) {
                assertNotNull(item);
                itemCount++;
            }
            return itemCount;
        });

        assertEquals(100, count); // 예상되는 항목 수
    }
}