package org.primefaces.test;

import java.util.List;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;

@Named
@RequestScoped
public class FileUploadView {
     
    private UploadedFile file;
    private UploadedFiles files;
 
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadedFiles getFiles() {
        return files;
    }

    public void setFiles(UploadedFiles files) {
        this.files = files;
    }

    public void upload() {
        handle(file);
    }
     
    public void uploadMultiple() {
        if (files != null && files.getFiles() != null) {
            List<UploadedFile> fileList = files.getFiles();
            for (UploadedFile file : fileList) {
                handle(file);
            }
        }
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        handle(event.getFile());
    }
    
    private void handle(UploadedFile file) {
        if (file != null && file.getFileName() != null && file.getContent() != null && file.getContent().length > 0) {
            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " " + formatSize(file.getContent()) + " is uploaded");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    private String [] sizes = {"Bytes", "KB", "MB", "GB", "TB"};
    
    private String formatSize(byte [] bytes) {
        if(bytes == null) {
            return "";
        }
        
        int size = bytes.length;

        if (size == 0) {
            return "N/A";
        }

        int i = (int)(Math.floor(Math.log(size) / Math.log(1024)));
        if (i == 0) {
            return size + ' ' + this.sizes[i];
        }
        else {
            return String.format(Locale.US, "%.1f %s", (size / Math.pow(1024, i)), this.sizes[i]);
        }
    }
     
}