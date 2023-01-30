package se.nina.projectee.flash;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashModelRepository extends JpaRepository<FlashModel, Long> {
    FlashModel findByUsername(String username);
}
