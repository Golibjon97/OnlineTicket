package com.ticket.onlineticket.Repository;

import com.ticket.onlineticket.Domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByHashId(String hashId);
}
