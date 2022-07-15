package org.generation.itemsApi.service;

import org.generation.itemsApi.Controller.dto.ItemDto;
import org.generation.itemsApi.entity.Item;

import java.util.List;

public interface ItemService {
    Item save( ItemDto itemDto );

    boolean delete( Integer itemId );

    Iterable<Item> findAll();

    Item findById( Integer itemId );

}
