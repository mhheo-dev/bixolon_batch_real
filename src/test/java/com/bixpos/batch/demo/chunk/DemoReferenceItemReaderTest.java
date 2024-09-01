package com.bixpos.batch.demo.chunk;

import com.bixpos.batch.business.domain.Reference;
import com.bixpos.batch.business.step.chunk.reader.DemoItemReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@ActiveProfiles("local")
public class DemoReferenceItemReaderTest {

    @Autowired
    private DemoItemReader reader;

    @Test
    public void testRead() throws Exception {
        Reference.DemoReference item;
        int count = 0;
        while ((item = reader.read()) != null) {
            assertNotNull(item);
            count++;
        }
        assertEquals(100, count); // 예상되는 항목 수
    }
}