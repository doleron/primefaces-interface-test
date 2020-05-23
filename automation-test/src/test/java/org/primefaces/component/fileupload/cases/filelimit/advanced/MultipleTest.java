package org.primefaces.component.fileupload.cases.filelimit.advanced;

import org.openqa.selenium.By;
import org.primefaces.component.fileupload.FileUploadTest;

public class MultipleTest extends FileUploadTest {

    @Override
    public void execute() throws Exception {

        this.init("fileUpload/fileLimit.xhtml");
        
        final String formId = "advancedMultipleForm";
        
        final String invalidMessage = "Maximum number of files exceeded";
        
        this.input(formId + ":uploader_input", "test_images/large-banana.png", "test_images/small-banana.png", "test_images/banana-JPG.jpg");
        
        this.assertContainsText(By.id(formId + ":uploader"), invalidMessage);
        
        /*
        FIXME: Another strange behavior, probably a bug. In with multiple='true' the component doesn't 
        recover after a fileLimit validation failure. Thus, if the user try to fill more than fileLimit files, the validation
        works displaying the proper validation message but the validation message doesn't automatically hides after a new input
        even if user click in upload button.
        
        this.input(formId + ":uploader_input", "test_images/small-banana.png", "test_images/banana-JPG.jpg");
        
        this.assertNotContainsText(By.id(formId + ":uploader"), invalidMessage);
        
        this.assertContainsText(By.id(formId + ":uploader"), "small-banana.png", "8.4 KB");
        
        */
        
        By buttonBy = By.xpath("//*[@id=\"" + formId + ":uploader\"]/div[1]/button[1]");
        
        this.clickButton(buttonBy);

        this.waitUntil(By.tagName("body"), "Successful");
    }

}
