package com.common.file.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import com.common.file.domain.MrAtchFile;
import com.common.file.repository.MrAtchFileRepository;
import com.common.file.service.MrAtchFileService;

@Service
public class MrAtchFileServiceImpl extends BaseService implements MrAtchFileService {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private MrAtchFileRepository mrAtchFileRepository;

    @Override
    public MrAtchFile getMrAtchFile(String code) {
        return mrAtchFileRepository.getMrAtchFile(code);
    }

    @Override
    public List<MrAtchFile> getMrAtchFileList(int mrReqNo, String mrStepCd) {
        return mrAtchFileRepository.getMrAtchFileList(mrReqNo, mrStepCd);
    }

    @Override
    public List<MrAtchFile> getMrAtchFileChrgrList(int mrReqNo, String mrStepCd, String chrgrClCd) {
        return mrAtchFileRepository.getMrAtchFileChrgrList(mrReqNo, mrStepCd, chrgrClCd);
    }

    @Override
    public MrAtchFile saveMrAtchFile(MrAtchFile mrAtchFile) {
        return saveMrAtchFiles(Arrays.asList(mrAtchFile)).get(0);
    }

    @Override
    public List<MrAtchFile> saveMrAtchFiles(List<MrAtchFile> mrAtchFiles) {
        return mrAtchFiles;
    }

    @Override
    public List<MrAtchFile> saveMrAtchChrgrFiles(List<MrAtchFile> mrAtchFiles) {
        return mrAtchFiles;
    }

    @Override
    public void deleteMrAtchFile(int code) {
        deleteMrAtchFiles(Arrays.asList(code));
    }

    @Override
    public void deleteMrAtchFiles(List<Integer> codes) {
        int deletedRowCount = 0;
        if (CollectionUtils.isNotEmpty(codes)) {
            deletedRowCount = mrAtchFileRepository.deleteMrAtchFiles(codes);
        }
    }

}