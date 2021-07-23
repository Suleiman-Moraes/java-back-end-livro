package br.com.moraes.userapi.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moraes.userapi.api.data.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findTopByCpf(String cpf);
	
	List<User> queryByNomeLike(String name);
}
