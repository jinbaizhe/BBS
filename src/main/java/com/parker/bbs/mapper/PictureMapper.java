package com.parker.bbs.mapper;

import com.parker.bbs.pojo.Picture;
import java.util.List;

public interface PictureMapper {
    int insertPicture(Picture picture);
    int deletePicture(Picture picture);
    Picture getPictureById(int id);
    List getPicturesByPostId(int id);
    List getPicturesByFollowpostId(int id);
}
