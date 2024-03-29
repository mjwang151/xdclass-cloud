package com.xdclass.service.impl;

import com.xdclass.domain.Video;
import com.xdclass.dao.VideoMapper;
import com.xdclass.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 旭瑶&小滴课堂 xdclass.net
 * @Author 二当家小D  代码、笔记和技术指导联系我即可
 * @Version 1.0
 **/

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public Video findById(int videoId) {
        return videoMapper.findById(videoId);
    }

}
