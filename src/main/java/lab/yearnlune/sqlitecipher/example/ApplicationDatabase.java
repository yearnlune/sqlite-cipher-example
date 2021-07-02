package lab.yearnlune.sqlitecipher.example;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.sqlite.SQLiteConfig;
import org.sqlite.mc.SQLiteMCConfig;
import org.sqlite.mc.SQLiteMCSqlCipherConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ApplicationDatabase {

	@Value("${spring.datasource.url:jdbc:sqlite:example.db}")
	private String url;

	@Value("${spring.datasource.password:2c8c9c14abff8525f6a0ffc97bcc6114}")
	private String password;

	private SQLiteMCConfig sqLiteConfig;

	private Connection connection;

	@PostConstruct
	protected void init() {
		initDbConfig();
	}

	@Bean
	public DataSource getDataSource() {
		try {
			System.out.println("getDataSource: " + url	);
			this.connection = sqLiteConfig.createConnection(url);
		} catch (SQLException | IllegalStateException e ) {
			e.printStackTrace();
		}

		return new SingleConnectionDataSource(connection, true);
	}

	@PreDestroy
	protected void preDestroy() {
		System.out.println("PRE DESTROY");
		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
			this.connection = null;
		}
	}

	private void initDbConfig() {
		log.info("INIT DB CONFIG");

		this.sqLiteConfig = SQLiteMCSqlCipherConfig
			.getV4Defaults()
			.withKey(password);
		this.sqLiteConfig.setJournalMode(SQLiteConfig.JournalMode.WAL);
	}
}
