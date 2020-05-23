package org.primefaces.component.fileupload.cases.allowtypes.advanced.auto;

import org.openqa.selenium.By;
import org.primefaces.component.fileupload.FileUploadTest;

public class MultipleTest extends FileUploadTest {

    @Override
    public void execute() throws Exception {

        this.init("fileUpload/allowTypes.xhtml");
        
        final String formId = "advancedMultipleAutoForm";
        
        final String invalidMessage = "Invalid file type";
        
        this.input(formId + ":uploader_input", "test_images/large-banana.png", "test_images/banana-JPG.jpg");
        
        this.assertContainsText(By.id(formId + ":uploader"), invalidMessage, "banana-JPG.jpg", "4.3 KB");
        
        this.input(formId + ":uploader_input", "test_images/small-banana.png", "test_images/banana-JPG.jpg");
        
        this.waitUntil(By.tagName("body"), "Successful");
    }

}
