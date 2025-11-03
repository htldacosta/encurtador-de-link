package tech.portifolio.urlshortener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import tech.portifolio.urlshortener.repository.UrlRepository;

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
	void contextLoads() {
	}

}
