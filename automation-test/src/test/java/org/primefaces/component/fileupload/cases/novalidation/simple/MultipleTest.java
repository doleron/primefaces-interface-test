package org.primefaces.component.fileupload.cases.novalidation.simple;

import org.openqa.selenium.By;
import org.primefaces.component.fileupload.FileUploadTest;

public class MultipleTest extends FileUploadTest {

    @Override
    public void execute() throws Exception {

        this.init("fileUpload/noValidation.xhtml");

        this.input("simpleMultipleForm:uploader_input", "test_images/large-banana.png", "test_images/banana-JPG.jpg", "test_images/small-banana.png");
        
        this.assertContainsText(By.id("simpleMultipleForm:uploader"), "large-banana.png 98.7 KB + 2");

        this.clickButton(By.id("simpleMultipleForm:send"));
        
        this.assertContainsText(By.tagName("body"), "large-banana.png 98.7 KB is uploaded", "banana-JPG.jpg 4.3 KB is uploaded", "small-banana.png 8.4 KB is uploaded");
    }

}
