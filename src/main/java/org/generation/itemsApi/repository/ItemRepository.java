package org.generation.itemsApi.repository;

import org.generation.itemsApi.entity.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Integer > {
}
