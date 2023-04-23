package com.cidenet.service;

import com.cidenet.entities.User;

public interface IUsersService {
    void AddUser(User user);

    User getUserByTypeDocumentAndNumDocument(String typeDocument, int numDocument);

    void updateUser(User user);

    void deleteUser(int numDocument);
}
