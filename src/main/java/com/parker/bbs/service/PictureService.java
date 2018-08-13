package com.parker.bbs.service;

import com.parker.bbs.pojo.Picture;

import java.io.File;

public interface PictureService {
    Picture getPictureById(int id);
    Picture uploadPicture(File file, String uploadFileName, String uploadContentType);

}
