package pl.cdv.ffr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.cdv.ffr.model.Image;
import pl.cdv.ffr.repository.ImageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    public List<Image> findAllImages() {
        return imageRepository.findAll();
    }

    public Image findImageById(String id) {
        Optional<Image> byId = imageRepository.findById(Long.parseLong(id));
        return byId.get();
    }

    public Image createImage(Image image) {
        Image newImage = new Image();
        newImage.setUrl(image.getUrl());
        return imageRepository.save(newImage);
    }

    public Image updateImage(Image newImage, String id) {
        return imageRepository.findById(Long.parseLong(id))
                .map(image -> {
                    image.setUrl(newImage.getUrl());
                    return imageRepository.save(image);
                })
                .orElseGet(() -> {
                    newImage.setId(Long.parseLong(id));
                    return imageRepository.save(newImage);
                });
    }

    public void deleteImage(String id) {
        imageRepository.deleteById(Long.parseLong(id));
    }
}