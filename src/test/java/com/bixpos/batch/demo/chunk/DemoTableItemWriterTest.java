package com.bixpos.batch.demo.chunk;

import com.bixpos.batch.business.domain.Table;
import com.bixpos.batch.business.step.chunk.writer.DemoItemWriter;
import com.bixpos.batch.business.mapper.DemoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.batch.test.StepScopeTestExecutionListener;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("local")
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        StepScopeTestExecutionListener.class
})
public class DemoTableItemWriterTest {

    @TestConfiguration
    static class TestConfig {
        @Bean
        @Primary
        @Scope(value = "step", proxyMode = ScopedProxyMode.TARGET_CLASS)
        public DemoItemWriter demoTableItemWriter(DemoMapper demoMapper) {
            return new DemoItemWriter(demoMapper);
        }
    }

    @Autowired
    private DemoItemWriter writer;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testWrite() throws Exception {
        List<Table.DemoTable> items = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Table.DemoTable item = new Table.DemoTable();
            item.setDemoColumn1("Test" + i);
            item.setDemoColumn2("Desc" + i);
            item.setDemoValue(i);
            items.add(item);
        }

        writer.write(items);

        try {
            int count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM BIXACCDT_BIXOLON.dbo.DEMO_TABLE WHERE DEMO_COLUMN1 LIKE 'Test%'",
                    Integer.class
            );
            assertEquals(5, count);
        } finally {
            // 테스트 데이터 삭제
            jdbcTemplate.update("DELETE FROM BIXACCDT_BIXOLON.dbo.DEMO_TABLE WHERE DEMO_COLUMN1 LIKE 'Test%'");
        }
    }
}