package com.cidenet.service.impl;

import com.cidenet.entities.User;
import com.cidenet.repository.UserRepository;
import com.cidenet.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements IUsersService {
     @Autowired
    UserRepository userRepository;
    @Override
    public void AddUser(User user) {
        userRepository.save(user);
    }
    @Override
    public User getUserByTypeDocumentAndNumDocument(String typeDocument, int numDocument) {
        User obj =  userRepository.findByTypeDocumentAndNumDocument(typeDocument, numDocument);
        return obj;
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(int numDocument) {
        userRepository.deleteByNumDocument(numDocument);
    }
}
