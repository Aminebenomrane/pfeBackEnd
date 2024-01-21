package com.example.pfebackend.repository;


import com.example.pfebackend.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByCreatedBy_IdOrAssignedTo_Id(Long createdByUserId, Long assignedToUserId);

}
