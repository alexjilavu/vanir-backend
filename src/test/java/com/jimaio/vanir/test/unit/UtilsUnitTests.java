package com.jimaio.vanir.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.jimaio.vanir.VanirApplication;
import com.jimaio.vanir.utils.AvatarUtils;
import com.jimaio.vanir.utils.Utils;

@RunWith(JUnit4.class)
@SpringBootTest(classes = VanirApplication.class,
		webEnvironment = WebEnvironment.RANDOM_PORT)
public class UtilsUnitTests {

	@Autowired
	private AvatarUtils avatarUtils;
	
	@Test
	public void avatarGeneration() {
		String avatarBasePath = "https://avataaars.io/png/";
		String generatedAvatar = avatarUtils.generateAvatarUrl();
		
		assertTrue(generatedAvatar.substring(avatarBasePath.length()).length() > 0);
	}
	
	@Test
	public void randomCardNumberGenerator() {
		int length = 16;
		String randomCode = Utils.generateRandomNumber(length);
		int codeLengthWithSpacing = length + length / 4 - 1;
		
		assertEquals(randomCode.length(), codeLengthWithSpacing);
	}
	
}
