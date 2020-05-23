package org.primefaces.component.fileupload.cases.novalidation;

import org.openqa.selenium.By;
import org.primefaces.component.fileupload.FileUploadTest;

public class AdvancedAutoTest extends FileUploadTest {

    @Override
    public void execute() throws Exception {

        this.init("fileUpload/noValidation.xhtml");

        this.input("advancedAutoForm:uploader_input", "test_images/large-banana.png");

        this.assertContainsText(By.id("advancedAutoForm:uploader"), "large-banana.png", "98.7 KB");

        this.waitUntil(By.tagName("body"), "large-banana.png 98.7 KB is uploaded");
    }

}
