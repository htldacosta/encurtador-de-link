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
import tech.portifolio.urlshortener.entities.UrlEntity;
import tech.portifolio.urlshortener.repository.UrlRepository;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
	@DisplayName("Deve redirecionar para URL original com sucesso")
	void shouldRedirectToOriginalUrl() throws Exception {
		var originalUrl = "http://urloriginal.com";
		var entity = new UrlEntity("12345", originalUrl, LocalDateTime.now().plusMinutes(10));
		urlRepository.save(entity);

		mockMvc.perform(get("/" + entity.getId()))
				.andExpect(status().isFound())
				.andExpect(header().string("Location", originalUrl));
	}

	@Test
	@DisplayName("Deve retornar 404 Not Found para um ID inexistente")
	void shouldReturnNotFoundForInvalidId() throws Exception {
		// 1. Preparação (Setup) - Nenhuma, o banco está limpo

		// 2. Ação (Act) & 3. Verificação (Assert)
		mockMvc.perform(get("/id-que-nao-existe"))
				.andExpect(status().isNotFound()); // Espera um HTTP 404
	}

	@Test
	@DisplayName("Deve retornar 400 Bad Request para URL inválida (sem http)")
	void shouldReturnBadRequestForInvalidUrl() throws Exception {
		// 1. Preparação (Setup)
		var request = new ShortenUrlRequest("url-invalida"); // Sem http://
		var jsonRequest = objectMapper.writeValueAsString(request);

		// 2. Ação (Act) & 3. Verificação (Assert)
		mockMvc.perform(post("/shorten-url")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isBadRequest()); // Espera um HTTP 400
	}

	@Test
	@DisplayName("Deve retornar 400 Bad Request para URL em branco")
	void shouldReturnBadRequestForBlankUrl() throws Exception {
		// 1. Preparação (Setup)
		var request = new ShortenUrlRequest(""); // URL em branco
		var jsonRequest = objectMapper.writeValueAsString(request);

		// 2. Ação (Act) & 3. Verificação (Assert)
		mockMvc.perform(post("/shorten-url")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isBadRequest()); // Espera um HTTP 400
	}

	@Test
	void contextLoads() {
	}

}
