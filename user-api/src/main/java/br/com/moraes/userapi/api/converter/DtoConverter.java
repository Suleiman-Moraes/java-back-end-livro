package br.com.moraes.userapi.api.converter;

import br.com.moraes.dto.UserDto;
import br.com.moraes.userapi.api.data.model.User;

public class DtoConverter {
	
	public static UserDto convert(User user) {
		UserDto userDTO = new UserDto();
        userDTO.setNome(user.getNome());
        userDTO.setEndereco(user.getEndereco());
        userDTO.setCpf(user.getCpf());
        userDTO.setKey(user.getKey());
        return userDTO;
    }
}
