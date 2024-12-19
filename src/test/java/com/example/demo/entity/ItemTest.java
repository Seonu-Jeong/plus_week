package com.example.demo.entity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ItemTest {

	@Autowired
	private TestEntityManager entityManager;

	@Test
	@DisplayName("status default 값 확인")
	public void itemStatusNullableFalseConstraintCheck () throws Exception {
		User owner = new User("user", "user1@gmail.com", "owner_nickname", "owner_password");
		User manager = new User("user", "user2@gmail.com", "manager_nickname", "manager_password");
		Item item = new Item("Ultra Dynamic TV", "4K FULL LED", manager, owner);

		entityManager.persist(manager);
		entityManager.persist(owner);
		entityManager.persist(item);
		entityManager.flush();

		Item savedItem = entityManager.refresh(item);

		assertThat(savedItem.getStatus(), is("PENDING"));
	}

}
