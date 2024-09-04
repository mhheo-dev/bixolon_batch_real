package com.bixpos.batch.demo.chunk;

import com.bixpos.batch.business.domain.Reference;
import com.bixpos.batch.business.domain.Table;
import com.bixpos.batch.business.step.chunk.DemoItemProcessor;
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
public class DemoItemProcessorTest {

    @Autowired
    private DemoItemProcessor processor;

    @Test
    public void testProcess() throws Exception {
        StepExecution stepExecution = MetaDataInstanceFactory.createStepExecution();

        StepScopeTestUtils.doInStepScope(stepExecution, () -> {
            Reference.DemoReference input = new Reference.DemoReference();
            input.setId(9999);
            input.setDemoValue(10);
            input.setDemoColumn1("VALUE9999");
            input.setDemoColumn2("VALUE9999");

            Table.DemoTable output = processor.process(input);

            assertNotNull(output);
            assertEquals(11, output.getDemoValue());
            return null;
        });
    }
}