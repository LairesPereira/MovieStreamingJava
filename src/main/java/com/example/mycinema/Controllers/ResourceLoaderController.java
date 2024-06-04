package com.example.mycinema.Controllers;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
public class ResourceLoaderController {

    private final String PROVIDER_PATH = System.getProperty("user.dir");
    private String SOURCE_PATH = PROVIDER_PATH.substring(0, PROVIDER_PATH.lastIndexOf("/")) + "/movies";

    @GetMapping("browse/video")
    public ResponseEntity<ResourceRegion> getMedia(@RequestHeader HttpHeaders headers, @RequestParam String title) {
        System.out.println(title);
        Resource media = new FileSystemResource(findResource(title));
        ResourceRegion region = resourceRegion(media, headers);
        return ResponseEntity
                .status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(media).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(region);
    }

    private ResourceRegion resourceRegion(Resource media, HttpHeaders headers) {
        long contentLength = 0;
        System.out.printf("Headers Range: %s", headers.getRange());
        try {
            contentLength = 100000;
            long contentLengthoriginal = media.contentLength();
            System.out.printf("Media content-original-len: %d \n", contentLengthoriginal);
            System.err.println("***");
            System.out.printf("Contente retrive: %d \n", contentLength);
        } catch (IOException e) {
            System.err.println(e);
        }
        List<HttpRange> range = headers.getRange();
        if (!range.isEmpty()) {
            String[] ranges = range.get(0).toString().split("-");
            System.out.println(Arrays.toString(range.get(0).toString().split("-")));
            long start = Long.parseLong(ranges[0]);
            long end;

            try {
                end =  Long.parseLong(ranges[1]);
            } catch (Exception e) {
                end = 100000;
            }

            //            long end = 10000;
//          long end = ranges.length > 1 ? Long.parseLong(ranges[1]) : contentLength - 1;
            long rangeLength = 10000;
            return new ResourceRegion(media, start, end);
        } else {
            long rangeLength = Math.min(1024 * 1024, contentLength);
            return new ResourceRegion(media, 0, rangeLength);
        }
    }

    private String findResource(String title) {
        File[] moviesDir = new File(SOURCE_PATH).listFiles();
        for (File movieDir: moviesDir) {
            if(movieDir.isDirectory()) {
                File[] subFiles = new File(movieDir.getPath()).listFiles();
                for (File file : subFiles) {
                    if(file.getName().endsWith(".mp4") || file.getName().endsWith(".mkv")) {
                        if(file.getName().contains(title)){
                            return file.getParent() + "/" + file.getName();
                        }
                    }
                }
            }
        }
        return "";
    }
}
