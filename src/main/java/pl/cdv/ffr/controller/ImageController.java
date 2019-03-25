package pl.cdv.ffr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.cdv.ffr.model.Image;
import pl.cdv.ffr.service.ImageService;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ImageController {

    @Autowired
    ImageService imageService;

    @RequestMapping(path = "/images", method = RequestMethod.GET)
    public List<Image> getAllImages() {
        return imageService.findAllImages();
    }

    @RequestMapping(path = "/images/{id}", method = RequestMethod.GET)
    public Image getImage(@PathVariable("id") String id) {
        return imageService.findImageById(id);
    }

    @RequestMapping(path = "/images", method = RequestMethod.POST)
    public Image createImage(@RequestBody Image image) {
        return imageService.createImage(image);
    }

    @RequestMapping(path = "/images/{id}", method = RequestMethod.PUT)
    public Image updateImage(@RequestBody Image image, @PathVariable("id") String id) {
        return imageService.updateImage(image, id);
    }

    @RequestMapping(path = "/images/{id}", method = RequestMethod.DELETE)
    public void deleteImage(@PathVariable("id") String id) {
        imageService.deleteImage(id);
    }
}