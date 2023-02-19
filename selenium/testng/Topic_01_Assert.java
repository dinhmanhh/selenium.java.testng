package testng;

import org.testng.Assert;

public class Topic_01_Assert {

	public static void main(String[] args) {
		String fullName = "Automation Testing";
		
		// Assert
		
		// Mong đợi điều kiện trả về là đúng
		Assert.assertTrue(3 < 5);
		Assert.assertTrue(fullName.contains("Automation"));
		
		// Mong đợi điều kiện trả về là sai
		Assert.assertFalse(fullName.contains("Manual"));
		
		// Mong đợi 2 điều kiện bằng nhau
		Assert.assertEquals(fullName, "Manual Testing");

	}

}
