package br.com.moraes.shoppingapi.api.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.moraes.dto.UserDto;
import br.com.moraes.shoppingapi.api.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Value("${USER_API_URL:http://localhost:8080}")
	private String apiURL;

	@Override
	public UserDto findTopByCpf(String cpf) {
		RestTemplate restTemplate = new RestTemplate();
		final String url = apiURL + "/user/cpf/" + cpf;
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

		ResponseEntity<UserDto> response = restTemplate.getForEntity(builder.toUriString(), UserDto.class);
		if (response.getStatusCodeValue() >= 300) {
			log.warn("Erro no findTopByCpf com status == {}.", response.getStatusCodeValue());
			return null;
		}
		return response.getBody();
	}
}
