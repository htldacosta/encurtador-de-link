package tech.portifolio.urlshortener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.portifolio.urlshortener.controller.dto.ShortenUrlRequest;
import tech.portifolio.urlshortener.repository.UrlRepository;

import static net.bytebuddy.matcher.ElementMatchers.is;

import static org.hamcrest.core.IsNull.notNullValue;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class UrlshortenerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UrlRepository urlRepository;

	@AfterEach
	void tearDown() {
		urlRepository.deleteAll();
	}

	@Test
	@DisplayName("Deve encurtar uma URL com sucesso")
	void shouldShortenUrlSucessoFully() throws Exception {
		var request = new ShortenUrlRequest("http://google.com.br");
		var jsonRequest = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/shorten-url")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.shortUrl", is(notNullValue())));

	}

	@Test
	void contextLoads() {
	}

}
