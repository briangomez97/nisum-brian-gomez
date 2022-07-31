package co.com.nisum;

import org.flywaydb.core.Flyway;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Primary
@EnableCaching
@ComponentScan("co.com.nisum")
public class ApplicationMock {

    @Bean
    public DataSource h2DataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource dataSource) {
        return Flyway.configure()
                .locations("filesystem:src/main/resources", "filesystem:src/test/resources").baselineOnMigrate(true)
                .dataSource(dataSource).load();

    }

}
