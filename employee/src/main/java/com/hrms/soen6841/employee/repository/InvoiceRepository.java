package com.hrms.soen6841.employee.repository;

import com.hrms.soen6841.employee.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{
}
