package org.primefaces.component.fileupload.cases.novalidation.simple;

import org.openqa.selenium.By;
import org.primefaces.component.fileupload.FileUploadTest;

public class SingleTest extends FileUploadTest {

    @Override
    public void execute() throws Exception {
        
        this.init("fileUpload/noValidation.xhtml");
        
        //interact with component
        this.input("simpleForm:uploader_input", "test_images/large-banana.png");
        
        //assert displayed message before send
        this.assertContainsText(By.id("simpleForm:uploader"), "large-banana.png 98.7 KB");
        
        //send
        this.clickButton(By.id("simpleForm:send"));
        
        //assert upload successful confirmation message
        this.assertContainsText(By.tagName("body"), "large-banana.png 98.7 KB is uploaded");
    }

}
