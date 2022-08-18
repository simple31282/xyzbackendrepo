package org.generation.itemsApi.service;

import org.generation.itemsApi.Controller.dto.ItemDto;
import org.generation.itemsApi.entity.Item;
import org.generation.itemsApi.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public void delete(Integer itemId) {
        Optional<Item> itemToDeleteOptional = this.itemRepository.findById(itemId);
        if(itemToDeleteOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The item is not found.");
        }
        Item itemDeleted = itemToDeleteOptional.get();
        this.itemRepository.delete(itemDeleted);
        throw new ResponseStatusException(HttpStatus.OK,"The item deleted.");
    }

    @Override
    public Item update(ItemDto itemDTO, Integer itemId) {
        Optional<Item> optionalItemToUpdate = itemRepository.findById(itemId);
        if(optionalItemToUpdate.isEmpty()){
            return null;
        }
        Item itemToUpdateFromDB = optionalItemToUpdate.get();
//       //update the players information

        if(itemDTO.getName() == null){
            itemToUpdateFromDB.setName(itemToUpdateFromDB.getName());
        } else if(itemDTO.getName().isEmpty()) {
            itemToUpdateFromDB.setName(itemToUpdateFromDB.getName());
        } else {
            itemToUpdateFromDB.setName(itemDTO.getName());
        }


        itemToUpdateFromDB.setDescription(itemDTO.getDescription() != null ? itemDTO.getDescription() : itemToUpdateFromDB.getDescription());
        itemToUpdateFromDB.setImageURL(itemDTO.getImageURL() != null ? itemDTO.getImageURL() : itemToUpdateFromDB.getDescription());
        itemToUpdateFromDB.setPrice(itemDTO.getPrice() != null ? itemDTO.getPrice() : itemToUpdateFromDB.getPrice());
//        save the player back to the DB
//        return the player to the client
        return itemRepository.save(itemToUpdateFromDB);

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
