package com.hrms.soen6841.jwt.repository;

import com.hrms.soen6841.jwt.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{
}
