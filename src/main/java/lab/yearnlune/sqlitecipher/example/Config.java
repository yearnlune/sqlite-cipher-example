package lab.yearnlune.sqlitecipher.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Project : zeromon-app-backends
 * Created by IntelliJ IDEA
 * Author : DONGHWAN, KIM
 * DATE : 2021.06.22
 * DESCRIPTION :
 */

@Entity
@Table(name = "ot_config")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Config {
	@Id
	private String key;

	@Column(columnDefinition = "TEXT")
	private String value;
}
