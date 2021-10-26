package com.unistar.app3.controller;

import com.unistar.app3.model.UserMessage;
import com.unistar.app3.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

	@GetMapping(value = { "/hello", "/ping" }) 
	public String ping() {
		LOGGER.info("ping requested");
		return "welcome to helloSpring api...";
	}

	@GetMapping(value = { "/greeting" })
	public UserMessage greeting(@RequestParam(value = "name", defaultValue = "my kid") String name) {
		UserMessage message = new UserMessage();
		message.setName(name);
		message.setEpochInUTC(DateUtils.getNowString());
		message. setEpochSecond(0);
		LOGGER. info("greeting: " + message.toString());
		return message;
	}

	@GetMapping(value = {"/greetingl", "/greetingl/{name}" })
	public UserMessage greetingl(@PathVariable(required = false) String name) {
		if(name == null || name.isEmpty()){
			name = "my kid";
		}

		UserMessage message = new UserMessage();
		message.setName(name);
		message.setEpochInUTC(DateUtils.getNowString()); 
		message.setEpochSecond(0);
		LOGGER.info("greetingl: " + message.toString()); 
		return message;
	}

	@RequestMapping(value = "sendmessage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String sendMessageJson(@RequestBody UserMessage message){
		LOGGER.info("sendMessageJson: " + message.toString());
		return message.toString();
	}
}
