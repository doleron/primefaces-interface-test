package org.primefaces.component.fileupload.cases.novalidation;

import org.openqa.selenium.By;
import org.primefaces.component.fileupload.FileUploadTest;

public class AdvancedTest extends FileUploadTest {

    @Override
    public void execute() throws Exception {

        this.init("fileUpload/noValidation.xhtml");
        
        this.input("advancedForm:uploader_input", "test_images/large-banana.png");
        
        this.assertContainsText(By.id("advancedForm:uploader"), "large-banana.png", "98.7 KB");
        
        By buttonBy = By.xpath("//*[@id=\"advancedForm:uploader\"]/div[1]/button[1]");
        
        this.clickButton(buttonBy);
        
        this.waitUntil(By.tagName("body"), "large-banana.png 98.7 KB is uploaded");
    }

}
