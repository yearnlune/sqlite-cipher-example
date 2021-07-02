package lab.yearnlune.sqlitecipher.example.jpa;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lab.yearnlune.sqlitecipher.example.Config;
import lab.yearnlune.sqlitecipher.example.ConfigRepository;


public class ConfigRepositoryTest extends JpaTestSupport {

	@Autowired
	private ConfigRepository configRepository;

	@Override
	public void setUp() {
		configRepository.save(Config.builder()
			.key("EXAMPLE")
			.value("ANSWER")
			.build());
	}

	@Test
	public void findConfigByKey_existKey_shouldBeReturnedValue() {
		/* GIVEN */
		String key = "EXAMPLE";

		/* THEN */
		Optional<Config> configOptional	= configRepository.findConfigByKey(key);

		/* WHEN */
		assertThat(configOptional.get().getValue(), is("ANSWER"));
	}

	@Test
	public void findConfigByKey_notExistKey_shouldBeReturnedValue() {
		/* GIVEN */
		String key = "nodata";

		/* THEN */
		Optional<Config> configOptional	= configRepository.findConfigByKey(key);

		/* WHEN */
		assertThat(configOptional.isPresent(), is(false));
	}
}
