package org.primefaces.component.fileupload.cases.allowtypes.simple;

import org.openqa.selenium.By;
import org.primefaces.component.fileupload.FileUploadTest;

public class SingleTest extends FileUploadTest {

    @Override
    public void execute() throws Exception {
        
        this.init("fileUpload/allowTypes.xhtml");
        
        String formId = "simpleForm";
        
        final String invalidMessage = "Invalid file type";
        
        this.input(formId + ":uploader_input", "test_images/banana-JPG.jpg");
        
        this.assertContainsText(By.id(formId + ":uploader"), invalidMessage + ": banana-JPG.jpg 4.3 KB");
        
        this.input(formId + ":uploader_input", "test_images/small-banana.png");
        
        this.assertNotContainsText(By.id(formId + ":uploader"), invalidMessage);
        
        this.assertContainsText(By.id(formId + ":uploader"), "small-banana.png 8.4 KB");
        
        this.clickButton(By.id(formId + ":send"));
        
        this.assertContainsText(By.tagName("body"), "small-banana.png 8.4 KB is uploaded");
    }

}
