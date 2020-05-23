package org.primefaces.component.fileupload.cases.filelimit.advanced.auto;

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
        FIXME: There is a strange behavior, probably a bug. In auto mode with multiple='true' the component doesn't 
        recover after a fileLimit validation failure. Thus, if the user try to fill more than fileLimit files, the validation
        works displaying the proper validation message but the component no longer allow to input new files.
        
        TODO: Investigate and open new issue / PR.
        
        this.input(formId + ":uploader_input", "test_images/small-banana.png", "test_images/banana-JPG.jpg");
        
        this.waitUntil(By.tagName("body"), "Successful");
        
        */
    }

}
