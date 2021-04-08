package com.xdclass.service;

import com.xdclass.service.fallback.VideoServiceFallback;
import com.xdclass.domain.Video;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "xdclass-video-service" ,fallback = VideoServiceFallback.class)
public interface VideoService {

    @GetMapping("/api/v1/video/find_by_id")
    Video findById(@RequestParam("videoId") int videoId);


    @PostMapping("/api/v1/video/save")
    int save(@RequestBody Video video);

}
