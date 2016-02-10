package com.mmventures.study.board.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.mmventures.study.board.dao.BoardContentDao;
import com.mmventures.study.board.dao.BoardInfoDao;
import com.mmventures.study.board.dao.ContentCommentDao;
import com.mmventures.study.common.dao.FileInfoDao;
import com.mmventures.study.common.service.FileManageService;
import com.mmventures.study.core.domain.BoardContent;
import com.mmventures.study.core.domain.BoardInfo;
import com.mmventures.study.core.domain.CommonData;
import com.mmventures.study.core.domain.CommonField;
import com.mmventures.study.core.domain.ContentComment;
import com.mmventures.study.core.domain.FileInfo;
import com.mmventures.study.core.domain.TableRelationInfo;

@Service("boardContentService")
@Transactional
public class BoardContentService {
    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BoardContentDao boardContentDao;
    @Autowired
    private BoardInfoDao boardInfoDao;
    @Autowired
    private ContentCommentDao contentCommentDao;
    @Autowired
    private FileInfoDao fileInfoDao;

    @Autowired
    private FileManageService fileManageService;

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
	// boardInfo 가져오기
	BoardInfo boardInfo = boardInfoDao.getBoardInfo(boardId);

	// content 가져오기
	BoardContent content = boardContentDao.selectBoardContent(boardId,
		contentId);
	model.addAttribute("content", content);

	// 카테고리 활성화 시 카테고리 정보 가져오기
	if (boardInfo.isCategoryEnable()) {
	    // set categoryList
	    List<CommonData> categoryList = boardInfoDao
		    .getBoardCategoryList(boardId);

	    Map<Integer, CommonData> categoryMap = new HashMap<>();
	    for (CommonData category : categoryList) {
		categoryMap.put(category.getId(), category);
	    }
	    model.addAttribute("categoryMap", categoryMap);
	    model.addAttribute("category",
		    categoryMap.get(content.getCategoryId()));
	}

	// TODO file 가져오기
	List<FileInfo> fileList = fileInfoDao.selectContentFileList(contentId);
	model.addAttribute("fileList", fileList);

	// TODO comment 가져오기
	List<ContentComment> commentList = contentCommentDao
		.selectCommentList(contentId);
	model.addAttribute("commentList", commentList);

	/** 기타 model attribute. */
	model.addAttribute("boardId", boardId);
    }

    public void writeContentForm(Model model, int boardId) {
	// set boardId
	model.addAttribute("boardId", boardId);

	// set categoryList
	List<CommonData> categoryList = boardInfoDao
		.getBoardCategoryList(boardId);
	model.addAttribute("categoryList", categoryList);
    }

    public void writeBoardContent(final Model model,
	    final BoardContent boardContent, final MultipartFile file) {

	// Set commonField
	boardContent.setCommonField(makeCommonField("tester"));
	boardContentDao.insertBoardContent(boardContent);

	// File upload 있을경우 파일 저장
	if (file.getSize() > 0) {
	    // 파일 저장
	    // XXX 파일 저장 실패시 어찌할지 정하기
	    int fileId = 0;
	    try {
		fileId = fileManageService.saveFile(file);
	    } catch (IOException e) {
		LOG.error("File save fail.", e);
		return;
	    }

	    // Content - File 매핑
	    TableRelationInfo relationInfo = new TableRelationInfo();
	    relationInfo.setCategory(1004);
	    relationInfo.setPk1(boardContent.getId());
	    relationInfo.setPk2(fileId);
	    relationInfo.setCommonField(makeCommonField("tester"));

	    boardContentDao.insertContentFileMapping(relationInfo);
	}
    }

    public void modifyContent() {

    }

    public void deleteContent() {
	// TODO 컨텐트 삭제할때는 파일과 코멘트도 삭제
    }

    public void writeComment(final Model model, final Integer parentCommentId,
	    final ContentComment comment) {

	comment.setCommonField(makeCommonField("tester"));

	// TODO insert comment
	contentCommentDao.insertComment(comment);

	LOG.info("Inserted commentId={}", comment.getId());

	// TODO 상위 코멘트가 있을 경우 관계 테이블에 추가
	if (parentCommentId != null) {
	    TableRelationInfo commentRelationInfo = new TableRelationInfo();
	    commentRelationInfo.setCategory(4002);
	    commentRelationInfo.setPk1(parentCommentId);
	    commentRelationInfo.setPk2(0);
	    commentRelationInfo.setCommonField(makeCommonField("tester"));

	    contentCommentDao.insertCommentRelation(commentRelationInfo);
	}
    }

    public void modifyComment() {

    }

    public void deleteComment(final Integer commentId) {
	// TODO 하위 댓글이 있는지 확인 => 없으면 isDelete:Y, 있으면....
	ContentComment comment = contentCommentDao.selectComment(commentId);
	comment.getCommonField().setDelete(true);
	comment.getCommonField().setUpdateDate(new Date());
	comment.getCommonField().setModifier("tester");

	contentCommentDao.saveOrUpdateComment(comment);
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
