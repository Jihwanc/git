package com.mmventures.study.common.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mmventures.study.common.dao.FileInfoDao;
import com.mmventures.study.core.domain.CommonField;
import com.mmventures.study.core.domain.FileInfo;

/**
 * File management service.
 * 
 * @author jihwan
 *
 */
@Service("fileManageService")
public class FileManageService {
    /** File save path url. */
    private static final String FILE_SAVE_PATH_BASE = "/files";
    /** yyyyMMdd date formatter. */
    private final DateTimeFormatter dateTimeFormatter 
    	= new DateTimeFormatterBuilder()
	    .appendPattern("yyyyMMdd").toFormatter();

    /** FileInfo dao. */
    @Autowired
    private FileInfoDao fileInfoDao;

    /**
     * 파일 저장. 저장경로 : /files/YYYYMMDD/timestamp+파일명
     * 
     * @param uploadFile
     *            uploadedFile
     * @return fileInfoId
     * @throws IOException fileSaveFail
     */
    public final int saveFile(final MultipartFile uploadFile)
	    throws IOException {
	uploadFile.getOriginalFilename();

	// yyyyMMdd format.
	LocalDate now = LocalDate.now();
	String nowString = now.format(dateTimeFormatter);

	// Make savePath.
	String savePath = FILE_SAVE_PATH_BASE + "/" + nowString + "/";

	// Make fileName.
	String saveFileName = System.currentTimeMillis()
		+ uploadFile.getOriginalFilename();

	// Save file.
	File saveFile = new File(savePath + saveFileName);
	saveFile.mkdirs();
	saveFile.createNewFile();
	
	uploadFile.transferTo(saveFile);
	
	// Make fileInfo.
	FileInfo fileInfo = new FileInfo();
	fileInfo.setFileName(uploadFile.getOriginalFilename());
	fileInfo.setSaveUrl(savePath + saveFileName);
	fileInfo.setFileSize(Math.toIntExact(uploadFile.getSize()));
	fileInfo.setDownloadCount(0);
	
	// XXX 임시로...
	fileInfo.setCommonField(makeCommonField("tester"));

	fileInfoDao.insertFileInfo(fileInfo);

	return fileInfo.getId();
    }

    public void getFile() {

    }
    
    private CommonField makeCommonField(String userName) {
	Date now = new Date();

	CommonField commonField = new CommonField();
	commonField.setCreateDate(now);
	commonField.setUpdateDate(now);
	commonField.setDelete(false);
	commonField.setModifier(userName);

	return commonField;
    }
}
