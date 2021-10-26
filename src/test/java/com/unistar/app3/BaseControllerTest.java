package com.unistar.app3;

import com.unistar.app3.controller.BaseController; 
import com.unistar.app3.model.UserMessage;
import com.unistar.app3.utils.DateUtils; 
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc; 
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; 
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters 
class BaseControllerTest { 
 @Autowired
 private MockMvc mockMvc;

 @Autowired
 private BaseController controller;

 @Autowired
 private JacksonTester<UserMessage> jsonMessage;

 @Test
 void contextLoads() throws Exception { 
   assertThat(controller).isNotNull();
 }

 @Test
 void shouldReturnDefaultMessage() throws Exception { 
   this.mockMvc.perform(get("/greeting"))
       .andDo(print())
       .andExpect(status().isOk()) 
       .andExpect(content().string(containsString("my kid")));
 }

 @Test
 void sendMessageTest() throws Exception {
   UserMessage message = new UserMessage();
   message.setName("testName");
   message.setEpochInUTC(DateUtils.getNowString());
   message.setEpochSecond(0);
   String postContent = jsonMessage.write(message).getJson();

   this.mockMvc.perform(post("/sendmessage") 
       .contentType(MediaType.APPLICATION_JSON_VALUE).content(postContent))
	   .andExpect(status().isOk())
       .andExpect(status().isOk())
       .andExpect(content().string(containsString(message.toString())));
 }
}
