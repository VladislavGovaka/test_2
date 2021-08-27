package com.itransition.MyDiplom.service;


import com.itransition.MyDiplom.entity.CollectionDB;
import com.itransition.MyDiplom.entity.Item;
import com.itransition.MyDiplom.entity.User;
import com.itransition.MyDiplom.repository.CollectionRepository;
import com.itransition.MyDiplom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class CollectionService {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private UserService userService;

    public List<CollectionDB> findByAll() {
        return collectionRepository.findAll();
    }

    public void add(CollectionDB collectionDB) {
        collectionDB.setData(new Date());
        collectionDB.setUser(userService.getAuthenticationUser());
        collectionDB.setUrl("https://socialvk.ru/wp-content/uploads/avatarka-pustaya-vk_21.jpg");
        collectionRepository.save(collectionDB);

    }

    public void deleteByid(Long id){
        collectionRepository.deleteById(id);
    }


    public void change(Long id, String name, String description , String url) {
        CollectionDB collection = collectionRepository.getById(id);
        collection.setName(name);
        collection.setDescription(description);
        if (url!=null){
            collection.setUrl(url);
        }

        collectionRepository.save(collection);
    }



    public List<CollectionDB> findByUser_id(Long id) {
        return collectionRepository.findByUser_id(id);
    }
    public List<CollectionDB> findAll(){
        return collectionRepository.findAll();

    }

    public CollectionDB getCollectionById(Long id){
        return collectionRepository.getById(id);
    }



}
