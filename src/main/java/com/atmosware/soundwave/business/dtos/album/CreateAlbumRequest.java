package com.atmosware.soundwave.business.dtos.album;

import java.util.UUID;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAlbumRequest {

  private String name;
  private UUID artistId;
}
