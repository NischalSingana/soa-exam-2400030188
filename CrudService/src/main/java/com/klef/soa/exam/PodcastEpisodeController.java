package com.klef.soa.exam;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class PodcastEpisodeController 
{

private final PodcastEpisodeService service;
public PodcastEpisodeController(PodcastEpisodeService service) 
{
    this.service = service;
}

@GetMapping("/")
public String home() {
    return "Podcast Epsidode Crud Service";
}
@PostMapping("/Add")
public ResponseEntity<String> addEpisode(
        @RequestBody PodcastEpisode episode) 
{
    episode.setId(null);
    PodcastEpisode savedEpisode = service.addEpisode(episode);
    return ResponseEntity.status(201)
            .body("Podcast Episode Added Successfully. ID: "
                    + savedEpisode.getId());
}
@GetMapping("/DisplayAll")
public ResponseEntity<?> displayAllEpisodes() 
{
    List<PodcastEpisode> episodes = service.displayAllEpisodes();
    if (episodes.size() > 0) {
        return ResponseEntity.ok(episodes);
    } else {
        return ResponseEntity.status(404)
                .body("Podcast Episode Data Not Found");
    }
}
@DeleteMapping("/Delete/{id}")
public ResponseEntity<String> deleteEpisode
(
        @PathVariable Long id) {
    boolean deleted = service.deleteEpisode(id);
    if (deleted) {
        return ResponseEntity.ok(
                "Podcast Episode Deleted Successfully");
    } 
    
    else 
    {
        return ResponseEntity.status(404)
                .body("Podcast Episode Not Found");
    }
}

}