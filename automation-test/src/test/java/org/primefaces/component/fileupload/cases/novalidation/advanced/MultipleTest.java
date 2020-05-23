package org.primefaces.component.fileupload.cases.novalidation.advanced;

import org.openqa.selenium.By;
import org.primefaces.component.fileupload.FileUploadTest;

public class MultipleTest extends FileUploadTest {

    @Override
    public void execute() throws Exception {

        this.init("fileUpload/noValidation.xhtml");
        
        this.input("advancedMultipleForm:uploader_input", "test_images/large-banana.png", "test_images/banana-JPG.jpg", "test_images/small-banana.png");
        
        this.assertContainsText(By.id("advancedMultipleForm:uploader"), "large-banana.png", "98.7 KB", "banana-JPG.jpg", "4.3 KB", "small-banana.png", "8.4 KB");
        
        By buttonBy = By.xpath("//*[@id=\"advancedMultipleForm:uploader\"]/div[1]/button[1]");
        
        this.clickButton(buttonBy);

        this.waitUntil(By.tagName("body"), "Successful");
    }

}
