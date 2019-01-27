package neueda.tinyurl.orm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;

import neueda.tinyurl.controller.Controller;
import neueda.tinyurl.controller.Response;
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(classes = {Controller.class,TinyUrlDbService.class})
public class ControllerTest extends AbstractJUnit4SpringContextTests{
	@Autowired
	Controller controller;
	
	@Test
	public void greetingTest() {
		Response url = new Response();
		url.setIpAddr("37.228.244.39");
		url.setResponseString("http://skype.com/accumsan/odio.jsp?sollicitudin=auctor&mi=gravida&sit=sem&amet=praesent&lobortis=id&sapien=massa&sapien=id&non=nisl&mi=venenatis&integer=lacinia&ac=aenean&neque=sit&duis=amet&bibendum=justo&morbi=morbi&non=ut&quam=odio");
		
		String json = new Gson().toJson(url);
		String response = controller.greeting(json);
		assertTrue(response.contains("http://localhost:8080/8d572168"));
	}
	
	
	@Test
	public void greetingTest_1() {
		Response url = new Response();
		url.setResponseString("skype.com");
		
		String json = new Gson().toJson(url);
		String response = controller.greeting(json);
		assertTrue(response.contains("Invalid"));
	}
	
	@Test
	public void getLongUrl() throws IOException {
		assertEquals(new ResponseEntity<>(HttpStatus.OK), controller.redirect("2b90340b"));
	
	}
	
}
