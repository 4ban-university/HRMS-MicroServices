package ca.concordia.soen6841.dbservice.repository;

import ca.concordia.soen6841.dbservice.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Long> {
    @Query("SELECT t FROM Tax t WHERE t.province = province AND (salary BETWEEN t.taxBracketMin AND t.taxBracketMax)")
    public Tax findByProvinceAndSalary(@Param("province") String province, @Param("salary") Integer salary);
}
