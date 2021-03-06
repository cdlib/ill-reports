package org.cdlib.ill.report;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@ImportResource("classpath:org/cdlib/ill/report/data-warehouse.xml")
@ComponentScan("org.cdlib.ill.report")
@PropertySources({
  @PropertySource("file:jreport-jdbc.properties")
  , @PropertySource("file:ssl.properties")
  , @PropertySource("classpath:application.properties")})
@SpringBootApplication
public class Application {

  @Value("${server.insecure-redirect-port}")
  private Integer insecureRedirectHttpsPort;
  @Value("${server.insecure-port}")
  private Integer insecureHttpPort;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public TomcatServletWebServerFactory servletContainer() {
    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
      @Override
      protected void postProcessContext(Context context) {
        SecurityConstraint constraint = new SecurityConstraint();
        constraint.setUserConstraint("CONFIDENTIAL");
        SecurityCollection collection = new SecurityCollection();
        collection.addPattern("/*");
        constraint.addCollection(collection);
        context.addConstraint(constraint);
      }
    };
    // add an additional connector to handle redirect of HTTP
    // traffic to the HTTPS port
    tomcat.addAdditionalTomcatConnectors(createCatalinaConnector());
    return tomcat;
  }

  /*
   * 
   * Create an HTTP connector that will redirect to the HTTPs port.
   */
  private Connector createCatalinaConnector() {
    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
    connector.setScheme("http");
    connector.setSecure(false);
    connector.setPort(insecureHttpPort);
    System.out.println("Redirecting HTTP traffic to port " + insecureRedirectHttpsPort);
    connector.setRedirectPort(insecureRedirectHttpsPort);
    return connector;
  }

}
