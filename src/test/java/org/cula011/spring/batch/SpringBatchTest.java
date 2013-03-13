package org.cula011.spring.batch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/database-config.xml",
        "classpath:spring/batch-repository-config.xml",
        "classpath:spring/batch-jobs-config.xml",
        "classpath:spring/spring-batch-toolbox-test.xml"})
public class SpringBatchTest
{
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier("csvFileToDatabaseJob")
    Job csvFileToDatabaseJob;

    @Autowired
    JdbcOperations jdbcTemplate;

    @Test
    public void shouldReadeFromCsvFileAndInsertIntoDatabase() throws JobExecutionException
    {
        JobParametersBuilder builder = new JobParametersBuilder();
        builder.addString("address.resource.path", "data/address.csv");
        jobLauncher.run(csvFileToDatabaseJob, builder.toJobParameters());

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM ADDRESS ORDER BY POSTCODE DESC");
        int rowCount = 0;
        while (rowSet.next())
        {
            rowCount++;
            String streetName = rowSet.getString("STREETNAME");
            LOGGER.info("Found value of '{}' for 'StreetName' in the database.", streetName);
        }
        assertThat(rowCount, is(2));
    }
}