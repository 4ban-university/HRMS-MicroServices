package ca.concordia.soen6841.dbservice.repository;

import ca.concordia.soen6841.dbservice.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{
    @Query(value = "SELECT * FROM hrms.invoice WHERE employee_id = ?1;", nativeQuery = true)
    public List<Invoice> findInvoiceByEmployeeId(Integer id);
}
