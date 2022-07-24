package com.cardservice.register.dao;

import org.springframework.data.repository.CrudRepository;
public interface CardManagementRepository extends CrudRepository<CardDetails, String> {
}
