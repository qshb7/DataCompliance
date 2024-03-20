package com.example.datacompliance.service.impl;

import com.example.datacompliance.entity.Classification;
import com.example.datacompliance.mapper.ClassificationMapper;
import com.example.datacompliance.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassificationServiceImpl implements ClassificationService {
    @Autowired
    private ClassificationMapper classificationMapper;

    @Override
    public void addClassification(Classification classification) {
        classificationMapper.addClassification(classification);
    }

    @Override
    public void updateClassification(Classification classification) {
        classificationMapper.updateClassification(classification);
    }

    @Override
    public void deleteClassification(Integer id) {
        classificationMapper.deleteClassification(id);
    }

    @Override
    public Classification getClassificationById(Integer id) {
        return classificationMapper.getClassificationById(id);
    }

    @Override
    public List<Classification> getAllClassifications() {
        return classificationMapper.getAllClassifications();
    }

}
