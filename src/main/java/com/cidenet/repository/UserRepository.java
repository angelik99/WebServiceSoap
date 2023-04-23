package com.cidenet.repository;

import com.cidenet.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByTypeDocumentAndNumDocument(String typeDocument, int numDocument);
    User deleteByNumDocument(int numDocument);
}
