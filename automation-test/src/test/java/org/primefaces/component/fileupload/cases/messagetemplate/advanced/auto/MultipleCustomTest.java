package org.primefaces.component.fileupload.cases.messagetemplate.advanced.auto;

import org.openqa.selenium.By;
import org.primefaces.component.fileupload.FileUploadTest;

public class MultipleCustomTest extends FileUploadTest {

    @Override
    public void execute() throws Exception {

        this.init("fileUpload/messageTemplate.xhtml");
        
        final String formId = "advancedMultipleAutoCustomForm";
        
        this.input(formId + ":uploader_input", "test_images/large-banana.png", "test_images/small-banana.png");
        
        this.assertContainsText(By.id(formId + ":uploader"), "The file name is large-banana.png and File size is 98.7 KB");
    }

}
