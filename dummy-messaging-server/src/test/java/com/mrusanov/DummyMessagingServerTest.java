package com.mrusanov;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mrusanov.DummyMessagingServerApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DummyMessagingServerApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class DummyMessagingServerTest {

	private static final String SEND_TEXT_URI = "/messages/send_text";

	private static final String SEND_EMOTION_URI = "/messages/send_emotion";
	
	private static final String UNSUPPORTED_TYPE_URI = "/messages/send_message";

	private static final String PAYLOAD_PARAM_KEY = "payload";

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testWithUnsupportedMessageType() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(UNSUPPORTED_TYPE_URI)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param(PAYLOAD_PARAM_KEY, "This is a message from unsupported type"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void testWithMissingPayloadParam() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(SEND_TEXT_URI)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void testWithEmptyPayloadParam() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(SEND_TEXT_URI)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param(PAYLOAD_PARAM_KEY, ""))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void testWithValidTextMessage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(SEND_TEXT_URI)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param(PAYLOAD_PARAM_KEY, "This is a text message!"))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void testWithTextMessageLongerThan160Characters() throws Exception {
		StringBuilder longLessage = new StringBuilder("T");
		while(longLessage.length() < 161) {
			longLessage.append("T");
		}
		
		mockMvc.perform(MockMvcRequestBuilders.post(SEND_TEXT_URI)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param(PAYLOAD_PARAM_KEY, longLessage.toString()))
				.andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
	}
	
	@Test
	public void testWithValidEmotionMessage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(SEND_EMOTION_URI)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param(PAYLOAD_PARAM_KEY, "Emotion"))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void testWithEmotionMessageShorterThan2Characters() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(SEND_EMOTION_URI)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param(PAYLOAD_PARAM_KEY, "E"))
				.andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
	}
	
	@Test
	public void testWithEmotionMessageLongerThan10Characters() throws Exception {
		StringBuilder longLessage = new StringBuilder("E");
		while(longLessage.length() < 11) {
			longLessage.append("E");
		}
		
		mockMvc.perform(MockMvcRequestBuilders.post(SEND_EMOTION_URI)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param(PAYLOAD_PARAM_KEY, longLessage.toString()))
				.andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
	}
	
	@Test
	public void testWithEmotionMessageThatContainsDigits() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(SEND_EMOTION_URI)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param(PAYLOAD_PARAM_KEY, "3m0t10n"))
				.andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
	}

}
