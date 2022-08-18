package org.generation.itemsApi.repository;

import org.generation.itemsApi.entity.AuthenticationToken;
import org.generation.itemsApi.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<AuthenticationToken,Integer> {

    AuthenticationToken findByUser(User user);
}
