package com.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration;


/**
 * Spring boot Main App!
 *
 */
@SpringBootApplication(exclude = ErrorWebFluxAutoConfiguration.class)

public class App 
{
    public static void main( String[] args )
    {
		SpringApplication.run(App.class, args);
	   /* ConnectionFactory connectionFactory = ConnectionFactories.get("r2dbc:h2:mem:///test?options=DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
	    DatabaseClient client = DatabaseClient.create(connectionFactory);
	    
	    client.execute("CREATE TABLE person" +
	            "(id VARCHAR(255) PRIMARY KEY," +
	            "name VARCHAR(255)," +
	            "age INT)")
	          .fetch()
	          .rowsUpdated()
	          .as(StepVerifier::create)
	          .expectNextCount(1)
	          .verifyComplete();
*/

    }
}
