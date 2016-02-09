package com.mmventures.study.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mmventures.study.board.service.BoardContentService;
import com.mmventures.study.board.service.BoardInfoService;
import com.mmventures.study.core.domain.BoardContent;
import com.mmventures.study.core.domain.ContentComment;
import com.mmventures.study.core.domain.UserInfo;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /** BoardContent service. */
    @Autowired
    private BoardContentService boardContentService;
    
    /** Board service. */
    @Autowired
    private BoardInfoService boardInfoService;

    /** Logger. */
    private static final Logger LOGGER = LoggerFactory
	    .getLogger(BoardController.class);

    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
    public final String contentList(
	    @RequestParam final int boardId,
//	    @RequestParam(required=false) final int page,
//	    @RequestParam(required=false) final int size,
//	    @RequestParam(required=false) final int category,
//	    @RequestParam(required=false) final String search,
	    final Model model) {
	
    	boardContentService.contentList(model, boardId, 0, 10, 0, "");
	
	return "board/list";
    }

    @RequestMapping(value = "/view.do", method = RequestMethod.GET)
    public final String viewContent(
	    @RequestParam final int boardId,
	    @RequestParam final int contentId,
	    final Model model) {
	
	boardContentService.contentDetail(model, boardId, contentId);
		
	
	return "board/view";
    }

    @RequestMapping(value = "/write.do", method = RequestMethod.GET)
    public final String writeContentForm(
	    @RequestParam final int boardId,
	    final Model model) {
	
	boardContentService.writeContentForm(model, boardId);
	
	return "board/write";
    }

    @RequestMapping(value = "/writeSubmit.do", method = RequestMethod.POST)
    public final String writeContent(
	    @ModelAttribute BoardContent boardContent,
	    final Model model) {
	
	System.out.println("# writeSubmit.do");
	System.out.println("# " + boardContent);
	
	boardContentService.writeBoardContent(model, boardContent);
	
	return "redirect:list";
    }

    @RequestMapping(value = "/modify.do", method = RequestMethod.GET)
    public final String modifyContentForm(final Model model) {
	return null;
    }

    @RequestMapping(value = "/modifySubmit.do", method = RequestMethod.POST)
    public final String modifyContent(final Model model) {
	return null;
    }

    @RequestMapping(value = "/fildDownload.do", method = RequestMethod.GET)
    public final String getFile(final Model model) {
	return null;
    }

    @RequestMapping(value = "/writeComment.do", method = RequestMethod.POST)
    public final String writeComment(
	    @RequestParam final int boardId,
	    @RequestParam final int contentId,
	    @RequestParam final int parentCommentId,
	    final Model model) {
	
	
	
	return null;
    }

    @RequestMapping(value = "/modifyComment.do", method = RequestMethod.POST)
    public final String modifyComment(
	    @ModelAttribute ContentComment comment,
	    final Model model) {
	
	
	
	return null;
    }

    @RequestMapping(value = "/deleteComment.do", method = RequestMethod.POST)
    public final String deleteComment(
	    @RequestParam final int commentId,
	    @ModelAttribute final UserInfo userInfo,
	    final Model model) {
	
	
	
	return null;
    }
}
