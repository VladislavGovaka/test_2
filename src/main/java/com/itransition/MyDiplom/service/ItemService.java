package com.itransition.MyDiplom.service;


import com.itransition.MyDiplom.entity.CollectionDB;
import com.itransition.MyDiplom.entity.Item;
import com.itransition.MyDiplom.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private CollectionService collectionService;


    @Autowired
    private ItemRepository itemRepository;

    public void add(Item item , CollectionDB collection) {
        item.setData(new Date());
        item.setCollection(collection);
        itemRepository.save(item);

    }
    public List<Item> getListItem() {
        return itemRepository.findAll();
    }

    public List<Item> getListItemUser(Long id) {
        List<CollectionDB> collections =  collectionService.findByUser_id(id);
        List<Item> itemList = new ArrayList<>();
        for (CollectionDB collection : collections) {
            for (Item item : collection.getItems()) {
                itemList.add(item);
            }
        }
        System.out.println("servis");
        for (Item item: itemList) {
            System.out.println(item.getName());
        }
        return itemList;
    }

    public void change(Item item) {
        if (findById(item.getId())!=null) {
            itemRepository.save(item);

        }
    }

    public void deleteItem(Long id) {
        if (findById(id)!=null) {
            itemRepository.deleteById(id);
        }
    }
    public Item findById(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.orElse(null);
    }
    public List<Item> findAllByCollectionId(Long id) {
        return collectionService.getCollectionById(id).getItems();
    }

    public void changeItem(Long id, String name, String description , String url) {
        Item item = itemRepository.getById(id);
        item.setName(name);
        item.setDescription(description);
        if (url!=null){
            item.setUrl(url);
        }

        itemRepository.save(item);
    }

}
