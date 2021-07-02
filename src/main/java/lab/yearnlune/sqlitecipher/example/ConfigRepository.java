package lab.yearnlune.sqlitecipher.example;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends JpaRepository<Config, String> {
	Optional<Config> findConfigByKey(String key);
}
