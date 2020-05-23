package org.primefaces.component.fileupload.cases.filelimit.simple;

import org.openqa.selenium.By;
import org.primefaces.component.fileupload.FileUploadTest;

public class MultipleCustomTest extends FileUploadTest {

    @Override
    public void execute() throws Exception {
        
        this.init("fileUpload/fileLimit.xhtml");
        
        String formId = "simpleMultipleCustomForm";
        
        final String invalidMessage = "Max number of files reached";
        
        this.input(formId + ":uploader_input", "test_images/large-banana.png", "test_images/small-banana.png", "test_images/banana-JPG.jpg");
        
        this.assertContainsText(By.id(formId + ":uploader"), invalidMessage);
        
        this.input(formId + ":uploader_input", "test_images/small-banana.png", "test_images/banana-JPG.jpg");
        
        this.assertNotContainsText(By.id(formId + ":uploader"), invalidMessage);
        
        this.assertContainsText(By.id(formId + ":uploader"), "small-banana.png 8.4 KB + 1");
        
        this.clickButton(By.id(formId + ":send"));
        
        this.assertContainsText(By.tagName("body"), "small-banana.png 8.4 KB is uploaded", "banana-JPG.jpg 4.3 KB is uploaded");
    }

}
