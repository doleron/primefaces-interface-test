package org.primefaces.component.fileupload.cases.sizelimit.addvanced.auto;

import org.openqa.selenium.By;
import org.primefaces.component.fileupload.FileUploadTest;

public class MultipleTest extends FileUploadTest {

    @Override
    public void execute() throws Exception {

        this.init("fileUpload/sizeLimit.xhtml");
        
        final String formId = "advancedMultipleAutoForm";
        
        final String invalidMessage = "Invalid file size";
        
        this.input(formId + ":uploader_input", "test_images/large-banana.png", "test_images/small-banana.png");
        
        this.assertContainsText(By.id(formId + ":uploader"), invalidMessage, "large-banana.png", "98.7 KB");
        
        this.input(formId + ":uploader_input", "test_images/small-banana.png", "test_images/banana-JPG.jpg");
        
        this.waitUntil(By.tagName("body"), "Successful");
    }

}
