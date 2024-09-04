package com.bixpos.batch.demo.chunk;

import com.bixpos.batch.business.domain.Table;
import com.bixpos.batch.business.step.chunk.DemoItemWriter;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.StepScopeTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("local")
public class DemoTableItemWriterTest {

    @Autowired
    private DemoItemWriter writer;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testWrite() throws Exception {
        StepExecution stepExecution = MetaDataInstanceFactory.createStepExecution();

        StepScopeTestUtils.doInStepScope(stepExecution, () -> {
            List<Table.DemoTable> items = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
                Table.DemoTable item = new Table.DemoTable();
                item.setDemoColumn1("Test" + i);
                item.setDemoColumn2("Desc" + i);
                item.setDemoValue(i);
                items.add(item);
            }

            writer.write(items);

            int count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM BIXACCDT_BIXOLON.dbo.DEMO_TABLE WHERE DEMO_COLUMN1 LIKE 'Test%'",
                    Integer.class
            );
            assertEquals(5, count);

            // 테스트 데이터 삭제
            jdbcTemplate.update("DELETE FROM BIXACCDT_BIXOLON.dbo.DEMO_TABLE WHERE DEMO_COLUMN1 LIKE 'Test%'");

            return null;
        });
    }
}