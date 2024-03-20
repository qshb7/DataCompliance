package com.example.datacompliance.service;

import com.example.datacompliance.entity.Classification;

import java.util.List;

public interface ClassificationService {
    void addClassification(Classification classification);

    void updateClassification(Classification classification);

    void deleteClassification(Integer id);

    Classification getClassificationById(Integer id);

    List<Classification> getAllClassifications();
}
