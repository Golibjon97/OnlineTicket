package com.ticket.onlineticket.Domain;


import com.ticket.onlineticket.Enum.FileStorageStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Image implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String extension;

    private Long fileSize;

    private String hashId;

    private String contentType;

    @Enumerated(EnumType.STRING)
    private FileStorageStatus fileStorageStatus;

    private String uploadPath;

    public Image(Long id, String name, String extension, Long fileSize, String hashId, String contentType, FileStorageStatus fileStorageStatus, String uploadPath) {
        this.id = id;
        this.name = name;
        this.extension = extension;
        this.fileSize = fileSize;
        this.hashId = hashId;
        this.contentType = contentType;
        this.fileStorageStatus = fileStorageStatus;
        this.uploadPath = uploadPath;
    }

    public Image() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public FileStorageStatus getFileStorageStatus() {
        return fileStorageStatus;
    }

    public void setFileStorageStatus(FileStorageStatus fileStorageStatus) {
        this.fileStorageStatus = fileStorageStatus;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}
