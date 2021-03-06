package org.primefaces.component.fileupload.cases.sizelimit.simple;

import org.openqa.selenium.By;
import org.primefaces.component.fileupload.FileUploadTest;

public class MultipleCustomTest extends FileUploadTest {

    @Override
    public void execute() throws Exception {
        
        this.init("fileUpload/sizeLimit.xhtml");
        
        String formId = "simpleMultipleCustomForm";
        
        final String invalidMessage = "File is too large";
        
        this.input(formId + ":uploader_input", "test_images/large-banana.png", "test_images/small-banana.png");
        
        this.assertContainsText(By.id(formId + ":uploader"), invalidMessage + ": large-banana.png 98.7 KB");
        
        this.input(formId + ":uploader_input", "test_images/small-banana.png", "test_images/banana-JPG.jpg");
        
        this.assertNotContainsText(By.id(formId + ":uploader"), invalidMessage);
        
        this.assertContainsText(By.id(formId + ":uploader"), "small-banana.png 8.4 KB + 1");
        
        this.clickButton(By.id(formId + ":send"));
        
        this.assertContainsText(By.tagName("body"), "small-banana.png 8.4 KB is uploaded", "banana-JPG.jpg 4.3 KB is uploaded");
    }

}
