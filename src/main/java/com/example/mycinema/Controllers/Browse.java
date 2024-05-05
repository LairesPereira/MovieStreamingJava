package com.example.mycinema.Controllers;

import com.example.mycinema.VideoStreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class Browse {

    @Autowired
    VideoStreamingService videoStreamingService;

    @GetMapping(value = "browse/video" , produces = "video/mp4")
    public Mono<Resource> getVideo(@RequestParam String title) {
        return videoStreamingService.getVideoStreaming(title);
    }
}
