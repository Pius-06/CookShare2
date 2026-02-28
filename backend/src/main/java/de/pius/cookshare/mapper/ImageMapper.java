package de.pius.cookshare.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import de.pius.cookshare.dto.image.ImageRequestDTO;
import de.pius.cookshare.model.Image;

@Component
public class ImageMapper {

    public static Image toImage(ImageRequestDTO dto) {
        return Image.builder()
                .originalName(dto.originalName())
                .contentType(dto.contentType())
                .build();
    }

    public static Set<Image> toImage(Set<ImageRequestDTO> dtos) {
        return dtos.stream()
                .map(imageDto -> toImage(imageDto))
                .collect(Collectors.toSet());
    }
}
