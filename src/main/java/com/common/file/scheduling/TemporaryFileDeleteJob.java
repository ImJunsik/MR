package com.common.file.scheduling;

import java.io.File;
import java.io.FileFilter;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.base.scheduling.JobAttributes;
import com.base.util.SpringApplicationContext;
import com.common.file.exception.TemporaryFileDeleteException;

/**
 * 임시파일 디렉토리에 있는 파일들을 Job Scheduler 통해 주기적으로 삭제한다.
 * {@linkplain context-scheduling.xml} 에 정의
 *
 * @author 조용상
 * @version 1.0
 * 
 * <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.01.27 조용상 최초 작성
 * </pre>
 */
public class TemporaryFileDeleteJob extends QuartzJobBean {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    protected void executeInternal(JobExecutionContext context) throws TemporaryFileDeleteException {
        MessageSource messageSource = SpringApplicationContext.getBean(MessageSource.class);
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String filename = null;

        try {
            String path = jobDataMap.getString("path");
            final int period = jobDataMap.getInt("period") == 0 ? 24 : jobDataMap.getInt("period");

            if (path == null) {
                throw new IllegalStateException(messageSource.getMessage("temp.file.path.error", null, Locale.KOREAN));
            }

            File temporaryDirectory = new File(path);

            if (temporaryDirectory.exists()) {
                FileFilter fileFilter = new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                        Date now = new Date();
                        int deletePeriod = Integer.parseInt(DurationFormatUtils.formatPeriod(
                                file.lastModified(), now.getTime(), "HH"));
                        return deletePeriod > period;
                    }
                };

                File[] files = temporaryDirectory.listFiles(fileFilter);

                if (logger.isDebugEnabled()) {
                    logger.debug(files.length + " temporary files deleting.");
                }

                for (File file : files) {
                    filename = file.getName();

                    if (logger.isDebugEnabled()) {
                        logger.debug("Filename ['" + file.getName() + "'] has been deleted.");
                    }

                    if (file.isFile()) {
                        FileUtils.forceDelete(file);
                    } else {
                        FileUtils.deleteDirectory(file);
                    }
                }

                if (logger.isDebugEnabled()) {
                    logger.debug(files.length + " temporary files deleted.");
                }
            } else {
                logger.warn("['" + path + "'] is not available");
            }
        } catch (SecurityException e) {
            throw new TemporaryFileDeleteException(messageSource, "temp.file.permission.error", new String[]{filename});
        } catch (Exception e) {
            jobDataMap.put(JobAttributes.JOB_DATA_PARAMETER_KEY, "a=123&b=456");
            logger.error(e.getMessage(), e);
            if (e instanceof TemporaryFileDeleteException) {
                throw (TemporaryFileDeleteException)e;
            } else {
                throw new TemporaryFileDeleteException(e.getMessage(), e);
            }
        }
    }
}