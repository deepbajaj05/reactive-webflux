package com.order;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

import com.order.controller.OrderController;
import com.order.domain.model.Orders;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.netty.http.client.HttpClient;

@ComponentScan(basePackages = "com.order")
@EnableAutoConfiguration
@Log4j2
// @WebFluxTest(ProductController.class)
// @RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderController.class, webEnvironment = WebEnvironment.RANDOM_PORT)
class OrderAPITest {
	@Autowired
	WebTestClient webTestClient;
	WebClient testClient = WebClient.builder()
            .baseUrl("http://localhost:8080")
            .clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection().compress(true)))
            .build();
	@Test
	public void testAllProducts() {
		
		  Flux<Orders> products = webTestClient .get() .uri("/product/all")
		  .accept(MediaType.APPLICATION_JSON) .exchange() .expectStatus().isOk() //
		  .expectHeader().valueEquals("content-type", "application/json")
		  .returnResult(Orders.class).getResponseBody();
		 
		  products
		  .log()
		  .doOnNext(product -> {
			 assertEquals(1, product.getStatus());
		  })
		.subscribe();
		    
	
			/*
			 * testClient .get() .uri("/product/all") .accept(MediaType.APPLICATION_JSON)
			 * .retrieve() .bodyToFlux(Product.class) .log() .subscribe();
			 */
		    
 
	}

	@Test
	public void testOneProduct() {
		
		  Orders firstProduct = webTestClient 
				  .get() .uri("/orders/all")
				  .accept(MediaType.APPLICATION_JSON) .exchange() .expectStatus().isOk() //
				  .expectHeader().valueEquals("content-type", "application/json")
				  .returnResult(Orders.class)
				  .getResponseBody()
				  .blockFirst();
		 
		  assertEquals(2, firstProduct.getStatus());		
		    
		  
	
			/*
			 * testClient .get() .uri("/product/all") .accept(MediaType.APPLICATION_JSON)
			 * .retrieve() .bodyToFlux(Product.class) .log() .subscribe();
			 */
	}
	
	@Test
	public void fluxTest() {
		
		
				  Flux<Orders> productFlux = webTestClient 
				  .get() 
				  .uri("/product/all")
				  .accept(MediaType.APPLICATION_JSON)
				  .exchange()
				  .returnResult(Orders.class)
				  .getResponseBody();
				  
				  productFlux.
				  subscribe((product->{
					  System.out.println("**************" + product);
				  }),
				   (error -> {
					   System.out.println("Errrrrrrrrrrrror" + error);
				   }),
				   (()->{
					   System.out.println("Compleeeeeeeeeeeted **********");
				   }));
				  
	}
}
