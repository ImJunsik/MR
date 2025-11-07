package com.common.file.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import com.common.file.domain.AttachFile;
import com.common.file.exception.AttachFileException;
import com.common.file.repository.AttachFileRepository;
import com.common.file.service.AttachFileService;

@Service
public class AttachFileServiceImpl extends BaseService implements AttachFileService {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private AttachFileRepository attachFileRepository;

    @Override
    public AttachFile getAttachFile(String code) {
        return attachFileRepository.getAttachFile(code);
    }

    @Override
    public AttachFile getAttachFileDrawMngNo(String drawMngNo) {
        return attachFileRepository.getAttachFileDrawMngNo(drawMngNo);
    }

    @Override
    public AttachFile getAttachFileDrawMngDevNo(String drawMngNo) {
        return attachFileRepository.getAttachFileDrawMngDevNo(drawMngNo);
    }
    
    @Override
    public AttachFile saveAttachFile(AttachFile attachFile) {
        return saveAttachFiles(Arrays.asList(attachFile)).get(0);
    }

    @Override
    public List<AttachFile> saveAttachFiles(List<AttachFile> attachFiles) {
        List<String> codes = new ArrayList<String>();

        try {
            if (CollectionUtils.isNotEmpty(attachFiles)) {
                for (AttachFile attachFile : attachFiles) {
                    if (StringUtils.isNotEmpty(attachFile.getCode())) {
                        attachFile.setDeleted(true);
                        codes.add(attachFile.getCode());
                    }

                    if (attachFile.isNew()) {
                        attachFile.setCode(AttachFile.CONVERSION.format(new Date()) + attachFile.getName().hashCode());
                        attachFileRepository.insertAttachFile(attachFile);
                        attachFile.transferMultipartFile(attachFile.getAttachedFile());
                    }
                }
                deleteAttachFiles(codes);
            }
        } catch (Exception e) {
            for (AttachFile attachFile : attachFiles) {
                try {
                    if (attachFile.isNew()) {
                        attachFile.deleteAttachedFile();
                    }
                } catch (IOException ioe) {
                    logger.error(ioe.getMessage(), ioe);
                }
            }
            throw new AttachFileException(messageSource, "attach.file.error", e);
        }
        return attachFiles;
    }

    @Override
    public void deleteAttachFile(String code) {
        deleteAttachFiles(Arrays.asList(code));
    }

    @Override
    public void deleteAttachFiles(List<String> codes) {
        int deletedRowCount = 0;

        if (CollectionUtils.isNotEmpty(codes)) {
            List<AttachFile> attachFiles = attachFileRepository.getAttachFileList(codes);
            File srcFile = null;
            File destFile = null;
            List<File> srcFiles = new ArrayList<File>();
            List<File> destFiles = new ArrayList<File>();

            deletedRowCount = attachFileRepository.deleteAttachFiles(codes);

            if (deletedRowCount == codes.size()) {
                try {
                    for(AttachFile attachFile : attachFiles) {
                        srcFile = attachFile.getAttachedFile();
                        destFile = new File(appConfig.getString("attach-path.temp") + File.separator + srcFile.getName());
                        FileUtils.copyFile(srcFile, destFile);

                        attachFile.deleteAttachedFile();

                        srcFiles.add(srcFile);
                        destFiles.add(destFile);
                    }
                } catch (Exception e) {
                    try {
                        for (int i = 0; i < srcFiles.size(); i++) {
                            FileUtils.copyFile(destFiles.get(i), srcFiles.get(i));
                        }
                    } catch (IOException ioe) {
                        logger.error(ioe.getMessage(), ioe);
                    }
                    throw new AttachFileException(messageSource, "attach.file.delete.error", e);
                }
            } else {
                throw new AttachFileException(messageSource, "attach.file.delete.error");
            }
        }
    }
}