package org.generation.itemsApi.service;

import org.generation.itemsApi.Controller.dto.ItemDto;
import org.generation.itemsApi.entity.Item;
import org.generation.itemsApi.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceMySQL implements ItemService{
    private final ItemRepository itemRepository;
    @Autowired
    public ItemServiceMySQL(ItemRepository itemRepository )
    {
        this.itemRepository = itemRepository;
    }
    @Override
    public Item save(ItemDto itemDto) {
        Item item = new Item(itemDto);
        return itemRepository.save(item);

    }

    @Override
    public boolean delete(Integer itemId) {
        return false;
    }


    @Override
    public Iterable<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item findById(Integer itemId) {
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if(optionalItem.isEmpty()) {
            return null;
        }
        Item item = optionalItem.get();
        return item;
    }
}
