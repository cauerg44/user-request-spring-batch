package com.devsuperior.user_request_sb.reader;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.devsuperior.user_request_sb.domain.ResponseUser;
import com.devsuperior.user_request_sb.dto.UserDTO;

@Configuration
public class FecthUserDataReaderConfig implements ItemReader<UserDTO> {

	private final String BASE_URL = "http://localhost:8081";
	private RestTemplate restTemplate = new RestTemplate();

	private int page = 0;

	@Override
	public UserDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub
		return null;
	}

	private List<UserDTO> fetchUserDataFromAPI() {
		
		String uri = BASE_URL + "/clients/pagedData?page=%d&size=%d";
		
		ResponseEntity<ResponseUser> response = restTemplate.exchange(String.format(uri, getPage()), HttpMethod.GET, null, 
				new ParameterizedTypeReference<ResponseUser>() {
				});
		
		List<UserDTO> result = response.getBody().getContent();
		return result;
	}

	public int getPage() {
		return page;
	}
}
