package com.parker.bbs.mapper;

import com.parker.bbs.pojo.Picture;
import java.util.List;

public interface PictureMapper {
    Integer insertPicture(Picture picture);
    Integer deletePicture(Picture picture);
    Picture getPictureById(Integer id);
    List getPicturesByPostId(Integer id);
    List getPicturesByFollowpostId(Integer id);
}
