package com.turkcell.soundwave.core.utilities.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.turkcell.soundwave.core.utilities.dtos.DeleteCloudinaryResponse;
import com.turkcell.soundwave.core.utilities.dtos.UploadCloudinaryResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class CloudinaryManager implements CloudinaryService{

    private Cloudinary cloudinary;


    @Override
    public UploadCloudinaryResponse upload(MultipartFile multipartFile) {
        try {
            Map<?,?> uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.emptyMap());
            return new UploadCloudinaryResponse(true, uploadResult);
        } catch (IOException e) {
            e.printStackTrace();
            return new UploadCloudinaryResponse(false,null);
        }
    }

    @Override
    public DeleteCloudinaryResponse delete(String publicIdOfSound) {

        List<String> publicIdsOfSounds = new ArrayList<String>();
        publicIdsOfSounds.add(publicIdOfSound);

        try {
            Map<?, ?> deleteResult = cloudinary.api().deleteResources(publicIdsOfSounds, ObjectUtils.emptyMap());
            return new DeleteCloudinaryResponse(true,deleteResult);
        } catch (Throwable e) {
            e.printStackTrace();
            return new DeleteCloudinaryResponse(false,null);
        }
    }
}
