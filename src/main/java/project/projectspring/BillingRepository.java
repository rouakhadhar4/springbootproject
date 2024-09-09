package project.projectspring;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository


public interface BillingRepository extends JpaRepository<Billing, Long> {
    List<Billing> findByNameContainingIgnoreCase(String name);
}
