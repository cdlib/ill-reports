package org.cdlib.ill.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 *
 * @author mmorrisp
 */
@ImportResource("classpath:org/cdlib/ill/report/data-warehouse.xml")
@ComponentScan("org.cdlib.ill.report")
@PropertySources({
    @PropertySource("file:jreport-jdbc.properties")
    , @PropertySource("classpath:spring-boot.properties")})
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
