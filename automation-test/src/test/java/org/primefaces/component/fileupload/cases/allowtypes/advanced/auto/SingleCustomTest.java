package org.primefaces.component.fileupload.cases.allowtypes.advanced.auto;

import org.openqa.selenium.By;
import org.primefaces.component.fileupload.FileUploadTest;

public class SingleCustomTest extends FileUploadTest {

    @Override
    public void execute() throws Exception {

        this.init("fileUpload/allowTypes.xhtml");
        
        final String formId = "advancedAutoCustomForm";
        
        final String invalidMessage = "File type not allowed";
        
        this.input(formId + ":uploader_input", "test_images/banana-JPG.jpg");
        
        this.assertContainsText(By.id(formId + ":uploader"), invalidMessage, "banana-JPG.jpg", "4.3 KB");
        
        this.input(formId + ":uploader_input", "test_images/small-banana.png");
        
        this.waitUntil(By.tagName("body"), "small-banana.png 8.4 KB is uploaded");
    }

}
