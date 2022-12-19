package com.library.management.repository;

import com.library.management.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepo extends JpaRepository<Publisher,Integer> {
    Publisher findByPublisherName(String publisherName);
}
