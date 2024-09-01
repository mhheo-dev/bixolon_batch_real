package com.bixpos.batch.demo.chunk;

import com.bixpos.batch.business.domain.Reference;
import com.bixpos.batch.business.domain.Table;
import com.bixpos.batch.business.step.chunk.processor.DemoItemProcessor;
import com.bixpos.batch.business.step.chunk.reader.DemoItemReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@ActiveProfiles("local")
public class DemoItemProcessorTest {

    @Autowired
    private DemoItemProcessor processor;

    @Test
    public void testProcess() throws Exception {
        Reference.DemoReference input = new Reference.DemoReference();
        input.setDemoValue(10);

        Table.DemoTable output = processor.process(input);

        assertNotNull(output);
        assertEquals(Optional.of(11), output.getDemoValue()); // DemoValue가 1 증가했는지 확인
    }
}