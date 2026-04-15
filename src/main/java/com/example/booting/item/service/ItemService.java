package com.example.booting.item.service;

import com.example.booting.item.entity.Item;
import com.example.booting.item.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository){
        this.repository = repository;
    }

    public Item saveItem(Item item){
        return repository.save(item);
    }

    public List<Item> findAll(){
        return repository.findAll();
    }
}
