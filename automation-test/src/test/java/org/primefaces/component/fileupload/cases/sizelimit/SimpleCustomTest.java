package org.primefaces.component.fileupload.cases.sizelimit;

import org.openqa.selenium.By;
import org.primefaces.component.fileupload.FileUploadTest;

public class SimpleCustomTest extends FileUploadTest {

    @Override
    public void execute() throws Exception {
        
        this.init("fileUpload/sizeLimit.xhtml");
        
        String formId = "simpleCustomForm";
        
        this.input(formId + ":uploader_input", "test_images/large-banana.png");
        
        this.assertContainsText(By.id(formId + ":uploader"), "File is too large: large-banana.png 98.7 KB");
        
        this.input(formId + ":uploader_input", "test_images/small-banana.png");
        
        this.assertNotContainsText(By.id(formId + ":uploader"), "File is too large");
        
        this.assertContainsText(By.id(formId + ":uploader"), "small-banana.png 8.4 KB");
        
        this.clickButton(By.id(formId + ":send"));
        
        this.assertContainsText(By.tagName("body"), "small-banana.png 8.4 KB is uploaded");
    }

}
