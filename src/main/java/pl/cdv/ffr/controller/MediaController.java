package pl.cdv.ffr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.cdv.ffr.model.Media;
import pl.cdv.ffr.service.MediaService;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MediaController {

    @Autowired
    MediaService mediaService;

    @RequestMapping(path = "/media", method = RequestMethod.GET)
    public List<Media> getAllMedia() {
        return mediaService.findAllMedia();
    }

    @RequestMapping(path = "/media/{id}", method = RequestMethod.GET)
    public Media getMedia(@PathVariable("id") String id) {
        return mediaService.findMediaById(id);
    }

    @RequestMapping(path = "/media", method = RequestMethod.POST)
    public Media createMedia(@RequestBody Media media) {
        return mediaService.createMedia(media);
    }

    @RequestMapping(path = "/media/{id}", method = RequestMethod.PUT)
    public Media updateMedia(@RequestBody Media media, @PathVariable("id") String id) {
        return mediaService.updateMedia(media, id);
    }

    @RequestMapping(path = "/media/{id}", method = RequestMethod.DELETE)
    public void deleteMedia(@PathVariable("id") String id) {
        mediaService.deleteMedia(id);
    }
}