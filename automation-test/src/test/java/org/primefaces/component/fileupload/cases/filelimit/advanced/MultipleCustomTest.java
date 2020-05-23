package org.primefaces.component.fileupload.cases.filelimit.advanced;

import org.openqa.selenium.By;
import org.primefaces.component.fileupload.FileUploadTest;

public class MultipleCustomTest extends FileUploadTest {

    @Override
    public void execute() throws Exception {

        this.init("fileUpload/fileLimit.xhtml");
        
        final String formId = "advancedMultipleCustomForm";
        
        final String invalidMessage = "Max number of files reached";
        
        this.input(formId + ":uploader_input", "test_images/large-banana.png", "test_images/small-banana.png", "test_images/banana-JPG.jpg");
        
        this.assertContainsText(By.id(formId + ":uploader"), invalidMessage);
        
        /*
        FIXME: Another strange behavior, probably a bug. In with multiple='true' the component doesn't 
        recover after a fileLimit validation failure. Thus, if the user try to fill more than fileLimit files, the validation
        works displaying the proper validation message but the validation message doesn't automatically hides after a new input
        even if user click in upload button.
        
        TODO: Investigate and open new issue / PR.
        
        this.input(formId + ":uploader_input", "test_images/small-banana.png", "test_images/banana-JPG.jpg");
        
        this.assertNotContainsText(By.id(formId + ":uploader"), invalidMessage);
        
        this.assertContainsText(By.id(formId + ":uploader"), "small-banana.png", "8.4 KB");
        
        */
        
        By buttonBy = By.xpath("//*[@id=\"" + formId + ":uploader\"]/div[1]/button[1]");
        
        this.clickButton(buttonBy);

        this.waitUntil(By.tagName("body"), "Successful");
    }

}
