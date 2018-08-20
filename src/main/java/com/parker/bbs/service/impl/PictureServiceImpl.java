package com.parker.bbs.service.impl;

import com.parker.bbs.annotation.LogAnnotation;
import com.parker.bbs.mapper.PictureMapper;
import com.parker.bbs.pojo.Picture;
import com.parker.bbs.service.PictureService;
import com.parker.bbs.util.OperationTarget;
import com.parker.bbs.util.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.File;

public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public Picture getPictureById(Integer id) {
        return pictureMapper.getPictureById(id);
    }

    @Override
    @LogAnnotation(operationType = OperationType.Insert, operationTarget = OperationTarget.Picture, operationInfo = "上传图片")
    public Picture uploadPictureNeedLog(File file, String uploadFileName, String uploadContentType) {
        return null;
    }
}
