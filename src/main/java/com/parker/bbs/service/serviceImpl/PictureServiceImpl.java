package com.parker.bbs.service.serviceImpl;

import com.parker.bbs.mapper.PictureMapper;
import com.parker.bbs.pojo.Picture;
import com.parker.bbs.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.File;

public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public Picture getPictureById(int id) {
        return pictureMapper.getPictureById(id);
    }

    @Override
    public Picture uploadPicture(File file, String uploadFileName, String uploadContentType) {
        return null;
    }
}
