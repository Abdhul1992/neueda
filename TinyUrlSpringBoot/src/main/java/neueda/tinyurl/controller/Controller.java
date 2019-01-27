package neueda.tinyurl.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.hash.Hashing;
import com.google.gson.Gson;

import neueda.tinyurl.orm.TinyUrlDbService;
import neueda.tinyurl.orm.UrlDao;

/**
 * This is the class that is the entry point for http request. 
 * @author absaleem
 *
 */
@RestController
@Component
public class Controller {
	@Autowired
	TinyUrlDbService tinyUrlDbService;
	private static final UrlValidator urlValidator = new UrlValidator(new String[] { "http", "https" });

	/**
	 * This method generates the short url from the long url by using hashing function.
	 * This is a post method. 
	 * A db service saves the url to the DB.
	 * This method also validates the long url.
	 * @param name
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/generateUrl", method = RequestMethod.POST, consumes = { "application/json" })
	public String generateUrl(@RequestBody String name) {
		Response response = getJsonFromResponseBody(name);
		if (urlValidator.isValid(response.getUrl())) {
			String id = getHashCodeFromUrl(response);
			Date date = new Date();
			Timestamp timeStampDate = new Timestamp(date.getTime());
			tinyUrlDbService.saveUrl(response, id, timeStampDate);
			Response url = new Response();
			url.setResponseString("http://localhost:8080/" + id);
			return new Gson().toJson(url);
			
		} else {
			Response url = new Response();
			url.setResponseString("Invalid url");
			return new Gson().toJson(url);
			
		}

	}

	/**
	 * This method is used to redirect the short url to the correct long url.
	 * @param name
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "{name}", method = RequestMethod.GET)
	public ResponseEntity<String> redirect(@PathVariable String name) throws IOException {
		UrlDao urlDao = tinyUrlDbService.getLongUrl(name);
		openBrowser(urlDao);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Hash function getter from the long url
	 * @param response
	 * @return
	 */
	private String getHashCodeFromUrl(Response response) {
		return Hashing.murmur3_32().hashString(response.getUrl(), StandardCharsets.UTF_8).toString();
		
	}

	private Response getJsonFromResponseBody(String name) {
		Gson gson = new Gson();
		return gson.fromJson(name, Response.class);
		
	}

	/**
	 * Open browser from the url
	 * @param urlDao
	 * @throws IOException
	 */
	private void openBrowser(UrlDao urlDao) throws IOException {
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("rundll32 url.dll,FileProtocolHandler " + urlDao.getLongUrl());
	}

}
