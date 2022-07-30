package co.com.nisum.repository;

import co.com.nisum.model.entity.RegularExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegularExpressionRepository extends JpaRepository<RegularExpression, String> {

    Optional<RegularExpression> findRegularExpressionByName(String name);
}
