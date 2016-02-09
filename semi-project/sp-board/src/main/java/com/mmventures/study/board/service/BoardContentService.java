package com.mmventures.study.board.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.mmventures.study.board.dao.BoardContentDao;
import com.mmventures.study.board.dao.BoardInfoDao;
import com.mmventures.study.board.dao.ContentCommentDao;
import com.mmventures.study.board.dao.FileInfoDao;
import com.mmventures.study.core.domain.BoardContent;
import com.mmventures.study.core.domain.BoardInfo;
import com.mmventures.study.core.domain.CommonData;
import com.mmventures.study.core.domain.CommonField;
import com.mmventures.study.core.domain.ContentComment;
import com.mmventures.study.core.domain.FileInfo;

@Service("boardContentService")
@Transactional
public class BoardContentService {
    @Autowired
    private BoardContentDao boardContentDao;
    @Autowired
    private BoardInfoDao boardInfoDao;
    @Autowired
    private ContentCommentDao contentCommentDao;
    @Autowired
    private FileInfoDao fileInfoDao;

    public BoardContentService() {

    }

    public void contentList(final Model model, final int boardId,
	    final int page, final int size, final int category,
	    final String search) {
	// TODO page 및 size에 따라 쿼리 변경

	// TODO 카테고리에 따라

	// TODO 검색에 따라

	List<BoardContent> contentList = boardContentDao
		.selectBoardContentList(boardId);

	model.addAttribute("contentList", contentList);
	model.addAttribute("boardId", boardId);
    }

    public void contentDetail(final Model model, final int boardId,
	    final int contentId) {
	// TODO boardInfo 가져오기
	BoardInfo boardInfo = boardInfoDao.getBoardInfo(boardId);

	// 카테고리 활성화 시 카테고리 정보 가져오기
	if (boardInfo.isCategoryEnable()) {
	    // set categoryList
	    List<CommonData> categoryList = boardInfoDao
		    .getBoardCategoryList(boardId);
	    
	    Map<Integer, CommonData> categoryMap = new HashMap<>();
	    for(CommonData category : categoryList) {
		categoryMap.put(category.getId(), category);
	    }
	    model.addAttribute("categoryMap", categoryMap);
	}

	// TODO content 가져오기
	BoardContent content = boardContentDao.selectBoardContent(boardId,
		contentId);
	model.addAttribute("content", content);

	// TODO file 가져오기
	List<FileInfo> fileList = fileInfoDao.selectContentFileList(contentId);
	model.addAttribute("fileList", fileList);

	// TODO comment 가져오기
	List<ContentComment> commentList = contentCommentDao
		.selectCommentList(contentId);
	model.addAttribute("commentList", commentList);

    }

    public void writeContentForm(Model model, int boardId) {
	// set boardId
	model.addAttribute("boardId", boardId);

	// set categoryList
	List<CommonData> categoryList = boardInfoDao
		.getBoardCategoryList(boardId);
	model.addAttribute("categoryList", categoryList);
    }

    public void writeContent() {

    }

    public void modifyContent() {

    }

    public void deleteContent() {
	// TODO 컨텐트 삭제할때는 파일과 코멘트도 삭제
    }

    public void writeComment() {

    }

    public void modifyComment() {

    }

    public void deleteComment() {
	// TODO 코멘트 삭제 시 하위 코멘트에 대한 로직도 생각
    }

    public void writeBoardContent(Model model, BoardContent boardContent) {

	Date now = new Date();

	CommonField commonField = new CommonField();
	commonField.setCreateDate(now);
	commonField.setUpdateDate(now);
	commonField.setDelete(false);
	commonField.setModifier("tester");

	boardContent.setCommonField(commonField);

	boardContentDao.insertBoardContent(boardContent);

    }

}
