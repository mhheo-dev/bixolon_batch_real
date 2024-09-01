package com.bixpos.batch.demo;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBatchTest
@SpringBootTest
@ActiveProfiles("local")
@Sql(scripts = {"classpath:com/bixpos/batch/demo/insert-demo-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"classpath:com/bixpos/batch/demo/clean-up-data.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class DemoBatchConfigTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testDemoJob() throws Exception {
        // Given
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        // When
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        // Then
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());

        // Verify the results
        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BIXACCDT_BIXOLON.dbo.DEMO_TABLE", Integer.class);
        assertEquals(100, count, "DEMO_TABLE should have 100 records");
    }
}