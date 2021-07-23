package br.com.moraes.userapi.api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moraes.userapi.api.data.model.User;
import br.com.moraes.userapi.api.repository.UserRepository;
import br.com.moraes.userapi.api.service.UserService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Getter
	@Autowired
	private UserRepository repository;

	@Override
	public Logger getLogger() {
		return log;
	}

	@Override
	public List<User> queryByName(String name) {
		final List<User> usuarios = repository.queryByNomeLike(name);
		return usuarios;
	}

	@Override
	public Optional<User> findTopByCpf(String cpf) {
		final Optional<User> user = repository.findTopByCpf(cpf);
		return user;
	}

	@Override
	public void preSave(User objeto) {
		if (objeto.getId() == null) {
			objeto.setKey(UUID.randomUUID().toString());
		}
	}
}
