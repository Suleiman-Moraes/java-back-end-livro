package br.com.moraes.userapi.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.moraes.dto.UserDto;
import br.com.moraes.exception.ResourceNotFoundException;
import br.com.moraes.userapi.api.converter.DtoConverter;
import br.com.moraes.userapi.api.data.model.User;
import br.com.moraes.userapi.api.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<UserDto>> findAll() {
		final List<User> usuarios = service.findAll();
		if (CollectionUtils.isEmpty(usuarios)) {
			throw new ResourceNotFoundException();
		}
		return ResponseEntity.ok(usuarios.stream().map(o -> DtoConverter.convert(o)).collect(Collectors.toList()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> findById(@PathVariable long id) {
		return ResponseEntity
				.ok(DtoConverter.convert(service.findById(id).orElseThrow(() -> new ResourceNotFoundException())));
	}

	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<UserDto> findTopByCpf(@PathVariable String cpf) {
		return ResponseEntity
				.ok(DtoConverter.convert(service.findTopByCpf(cpf).orElseThrow(() -> new ResourceNotFoundException())));
	}

	@GetMapping("/search")
	public ResponseEntity<List<UserDto>> queryByName(@RequestParam String nome) {
		final List<User> usuarios = service.queryByName(nome);
		if (CollectionUtils.isEmpty(usuarios)) {
			throw new ResourceNotFoundException();
		}
		return ResponseEntity.ok(usuarios.stream().map(o -> DtoConverter.convert(o)).collect(Collectors.toList()));
	}

	@PostMapping
	public ResponseEntity<UserDto> inserir(@RequestBody UserDto userDto) {
		final User user = service.create(User.convert(userDto));
		return ResponseEntity.status(HttpStatus.CREATED).body(DtoConverter.convert(user));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
