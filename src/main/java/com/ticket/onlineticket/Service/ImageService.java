package com.ticket.onlineticket.Service;

import com.ticket.onlineticket.Domain.Image;
import com.ticket.onlineticket.Enum.FileStorageStatus;
import com.ticket.onlineticket.Repository.ImageRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
public class ImageService {

    private ImageRepository imageRepository;

    @Value("${upload.folder}")
    private String uploadFolder;

    private final Hashids hashids;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
        this.hashids = new Hashids(getClass().getName(),6);
    }

    public void save(MultipartFile multipartFile){
        Image fileStorage = new Image();
        fileStorage.setName(multipartFile.getOriginalFilename());
        fileStorage.setExtension(getExt(multipartFile.getOriginalFilename()));
        fileStorage.setFileSize(multipartFile.getSize());
        fileStorage.setContentType(multipartFile.getContentType());
        fileStorage.setFileStorageStatus(FileStorageStatus.DRAFT);
        imageRepository.save(fileStorage);

        Date now = new Date();

        File uploadFolder = new File(String.format("%s/upload_files/%d/%d/%d", this.uploadFolder,
                1990+now.getYear(), 1+now.getMonth(), now.getDate()));
        if(!uploadFolder.exists() && uploadFolder.mkdirs()){
            System.out.println("created");
        }

        fileStorage.setHashId(hashids.encode(fileStorage.getId()));

        fileStorage.setUploadPath(String.format("upload_files/%d/%d/%d/%s.%s",
                1990+now.getYear(), 1+now.getMonth(), now.getDate(), fileStorage.getHashId(),fileStorage.getExtension()));
        imageRepository.save(fileStorage);
        uploadFolder = uploadFolder.getAbsoluteFile();
        File file = new File(uploadFolder, String.format("%s.%s", fileStorage.getHashId(), fileStorage.getExtension()));
        try{
            multipartFile.transferTo(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Transactional(readOnly = true)
    public Image read(String hashId){
        return imageRepository.findByHashId(hashId);
    }

    public void delete(String hashId){
        Image image = imageRepository.findByHashId(hashId);
        File file = new File(String.format("%s/%s", this.uploadFolder, image.getUploadPath()));
        if(file.delete()){
            imageRepository.delete(image);
        }
    }

    private String getExt(String fileName){
        String ext = null;
        if(fileName != null && !fileName.isEmpty()){
            int dot = fileName.lastIndexOf('.');
            if(dot > 0 && dot <= fileName.length() -2 ){
                ext = fileName.substring(dot + 1);
            }
        }
        return ext;
    }
}
