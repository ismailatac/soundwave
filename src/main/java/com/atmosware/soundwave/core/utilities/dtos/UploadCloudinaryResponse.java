package com.atmosware.soundwave.core.utilities.dtos;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadCloudinaryResponse {
    private boolean isSuccess;
    private Map<?,?> uploadResult;

}
