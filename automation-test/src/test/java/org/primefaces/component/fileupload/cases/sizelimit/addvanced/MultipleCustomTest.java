package org.primefaces.component.fileupload.cases.sizelimit.addvanced;

import org.openqa.selenium.By;
import org.primefaces.component.fileupload.FileUploadTest;

public class MultipleCustomTest extends FileUploadTest {

    @Override
    public void execute() throws Exception {

        this.init("fileUpload/sizeLimit.xhtml");
        
        final String formId = "advancedMultipleCustomForm";
        
        final String invalidMessage = "File is too large";
        
        this.input(formId + ":uploader_input", "test_images/large-banana.png", "test_images/small-banana.png");
        
        this.assertContainsText(By.id(formId + ":uploader"), invalidMessage, "large-banana.png", "98.7 KB");
        
        this.input(formId + ":uploader_input", "test_images/small-banana.png", "test_images/banana-JPG.jpg");
        
        this.assertNotContainsText(By.id(formId + ":uploader"), invalidMessage);
        
        this.assertContainsText(By.id(formId + ":uploader"), "small-banana.png", "8.4 KB");
        
        By buttonBy = By.xpath("//*[@id=\"" + formId + ":uploader\"]/div[1]/button[1]");
        
        this.clickButton(buttonBy);

        this.waitUntil(By.tagName("body"), "Successful");
    }

}
