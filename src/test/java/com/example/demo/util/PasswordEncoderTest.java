package com.example.demo.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PasswordEncoderTest {

	@Test
	@DisplayName("패스워드 암호화 전/후 비교")
	public void whenPasswordInserted_thenPasswordEncode() {
		String rawPassword = "abcde12345@";

		String encodedPassword =  PasswordEncoder.encode(rawPassword);

		assertThat(encodedPassword, not(equalTo(rawPassword)));
	}

	@Test
	@DisplayName("다른 패스워드 입력")
	public void whenOtherPasswordInserted_thenNotMatchPassword() {
		String originalPassword = "abcde12345@";
		String insertedPassword = "12345abcde#";
		String EncodedOriginalPassword = PasswordEncoder.encode(originalPassword);

		assertFalse(PasswordEncoder.matches(insertedPassword, EncodedOriginalPassword));
	}

	@Test
	@DisplayName("같은 패스워드 입력")
	public void whenOriginalPasswordInserted_thenMatchPassword() {
		String originalPassword = "abcde12345@";
		String EncodedOriginalPassword = PasswordEncoder.encode(originalPassword);
		String insertedPassword = originalPassword;

		assertTrue(PasswordEncoder.matches(insertedPassword, EncodedOriginalPassword));
	}

}
