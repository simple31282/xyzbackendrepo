package org.generation.itemsApi.Controller;

import org.generation.itemsApi.Controller.dto.ItemDto;
import org.generation.itemsApi.entity.Item;
import org.generation.itemsApi.repository.ItemRepository;
import org.generation.itemsApi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController {
    final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    @GetMapping("/all")
    public Iterable<Item> getItems() {
        return itemService.findAll();
    }
    @CrossOrigin
    @PostMapping("/add")
    public Item postItem(@RequestBody ItemDto itemDto ) {
        return itemService.save(itemDto);
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Integer id) {
        return itemService.findById(id);
    }

    @PutMapping( "/{id}" )
    public Item update( @RequestBody ItemDto itemDto, @PathVariable Integer id )
    {
        Item item = itemService.findById( id );
        item.setName( itemDto.getName() );
        item.setDescription( itemDto.getDescription() );
        item.setImageURL( itemDto.getImageURL() );
        return itemService.save(itemDto);
    }

    @DeleteMapping( "/{id}" )
    public void delete( @PathVariable Integer id )
    {
        itemService.delete( id );
    }
}
