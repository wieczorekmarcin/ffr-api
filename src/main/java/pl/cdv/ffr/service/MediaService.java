package pl.cdv.ffr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.cdv.ffr.model.Media;
import pl.cdv.ffr.repository.MediaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MediaService extends BaseService {

    @Autowired
    MediaRepository mediaRepository;

    public List<Media> findAllMedia() {
        return mediaRepository.findAll();
    }

    public Media findMediaById(String id) {
        Optional<Media> byId = mediaRepository.findById(Long.parseLong(id));
        return byId.get();
    }

    public Media createMedia(Media media) {
        return mediaRepository.save(media);
    }

    public Media updateMedia(Media newMedia, String id) {
        return mediaRepository.findById(Long.parseLong(id))
                .map(media -> {
                    copyNonNullProperties(newMedia, media);
                    return mediaRepository.save(media);
                })
                .orElseGet(() -> {
                    newMedia.setId(Long.parseLong(id));
                    return mediaRepository.save(newMedia);
                });
    }

    public void deleteMedia(String id) {
        mediaRepository.deleteById(Long.parseLong(id));
    }
}