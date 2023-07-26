package com.atmosware.soundwave.core.utilities.cloudinary;

import com.atmosware.soundwave.core.utilities.dtos.DeleteCloudinaryResponse;
import com.atmosware.soundwave.core.utilities.dtos.UploadCloudinaryResponse;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Slf4j
@Service
@AllArgsConstructor
public class CloudinaryManager implements CloudinaryService{

    private Cloudinary cloudinary;

    @Override
    public UploadCloudinaryResponse upload(MultipartFile multipartFile) {
        try {
            Map<?,?> uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.asMap("resource_type","auto"));
            return new UploadCloudinaryResponse(true, uploadResult);
        } catch (IOException e) {
            log.error(e.getMessage());
            return new UploadCloudinaryResponse(false,null);
        }
    }

    @Override
    public DeleteCloudinaryResponse delete(String publicIdOfSound) {

        List<String> publicIdsOfSounds = new ArrayList<>();
        publicIdsOfSounds.add(publicIdOfSound);

        try {
            Map<?, ?> deleteResult = cloudinary.api().deleteResources(publicIdsOfSounds, ObjectUtils.asMap("resource_type","auto"));
            return new DeleteCloudinaryResponse(true,deleteResult);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new DeleteCloudinaryResponse(false,null);
        }
    }
}
