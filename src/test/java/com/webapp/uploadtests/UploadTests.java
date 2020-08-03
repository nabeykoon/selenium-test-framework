package com.webapp.uploadtests;

import com.webapp.base.TestUtilities;
import com.webapp.pages.FileUploaderPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UploadTests extends TestUtilities {

	@Test
	public void imageUploadTest() {
		log.info("Starting imageUploadTest");

		// open File Uploader Page
		FileUploaderPage fileUploaderPage = new FileUploaderPage(driver, log);
		fileUploaderPage.openPage();

		// Select file
		String fileName = "Intel-logo.jpg";
		fileUploaderPage.selectFile(fileName);

		// Push upload button
		fileUploaderPage.pushUploadButton();
		sleep(2000);

		// Get uploaded files
		String fileNames = fileUploaderPage.getUploadedFilesNames();

		// Verify selected file uploaded
		Assert.assertTrue(fileNames.contains(fileName),
				"Our file (" + fileName + ") is not one of the uploaded (" + fileNames + ")");
	}
}
