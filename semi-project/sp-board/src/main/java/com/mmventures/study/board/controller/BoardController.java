package com.mmventures.study.board.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mmventures.study.board.service.BoardContentService;
import com.mmventures.study.board.service.BoardInfoService;
import com.mmventures.study.core.domain.BoardContent;
import com.mmventures.study.core.domain.ContentComment;
import com.mmventures.study.core.domain.UserInfo;

/**
 * Board controller.
 * 
 * @author jihwan
 *
 */
@Controller
@RequestMapping(value = "/board")
public class BoardController {
    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /** BoardContent service. */
    @Autowired
    private BoardContentService boardContentService;

    /** Board service. */
    @Autowired
    private BoardInfoService boardInfoService;

    /** Logger. */
    private static final Logger LOGGER = LoggerFactory
	    .getLogger(BoardController.class);

    /**
     * Get boardContent list.
     * 
     * @param boardId
     *            boardId
     * @param model
     *            model
     * @return viewName
     */
    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
    public final String contentList(@RequestParam final int boardId,
	    // @RequestParam(required=false) final int page,
	    // @RequestParam(required=false) final int size,
	    // @RequestParam(required=false) final int category,
	    // @RequestParam(required=false) final String search,
	    final Model model) {

	boardContentService.contentList(model, boardId, 0, 10, 0, "");

	return "board/list.ftl";
    }

    /**
     * Get content info.
     * 
     * @param boardId
     *            boardId
     * @param contentId
     *            contentId
     * @param model
     *            model
     * @param authentication
     *            authentication
     * @param httpSession
     *            httpSession
     * @return viewName
     */
    @RequestMapping(value = "/view.do", method = RequestMethod.GET)
    public final String viewContent(@RequestParam final int boardId,
	    @RequestParam final int contentId, final Model model,
	    final Authentication authentication,
	    final HttpSession httpSession) {

	boardContentService.contentDetail(model, boardId, contentId);

	if (authentication == null) {
	    model.addAttribute("isLogin", false);

	    LOGGER.info("User not login.");
	} else {
	    model.addAttribute("isLogin", true);

	    LOGGER.info("name={}", authentication.getName());
	    LOGGER.info("details={}", authentication.getDetails());
	    LOGGER.info("principal={}", authentication.getPrincipal());
	    LOGGER.info("credentials={}", authentication.getCredentials());
	}

	UserInfo userInfo = new UserInfo();
	userInfo.setId(1);
	userInfo.setName("테스터");
	userInfo.setNickName("테스터N");
	userInfo.setEmail("tester@tester.com");

	httpSession.setAttribute("userInfo", userInfo);

	return "board/view.ftl";
    }

    /**
     * Content write form.
     * 
     * @param boardId
     *            boardId
     * @param model
     *            model
     * @param authentication
     *            authentication
     * @return viewName
     */
    @RequestMapping(value = "/write.do", method = RequestMethod.GET)
    public final String writeContentForm(@RequestParam final int boardId,
	    final Model model, final Authentication authentication) {

	boardContentService.writeContentForm(model, boardId);

	return "board/write.ftl";
    }

    /**
     * 
     * 
     * @param boardContent
     *            content
     * @param file
     *            uploadFile
     * @param model
     *            model
     * @param authentication
     *            authentication
     * @return viewName
     */
    @RequestMapping(value = "/writeSubmit.do", method = RequestMethod.POST)
    public final String writeContent(
	    @ModelAttribute final BoardContent boardContent,
	    @RequestParam("file") final MultipartFile file, final Model model,
	    final Authentication authentication) {

	log.info("file upload. {}", file);
	log.info("name={}, contentType={}, originalFileName={}, size={}",
		file.getName(), file.getContentType(),
		file.getOriginalFilename(), file.getSize());

	boardContentService.writeBoardContent(model, boardContent, file);

	return "redirect:view.do?boardId=" + boardContent.getBoardId()
		+ "&contentId=" + boardContent.getId();
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

    /**
     * Write comment
     * 
     * @param parentCommentId
     *            parentCommentId
     * @param comment
     *            comment
     * @param model
     *            model
     * @return viewName
     */
    @RequestMapping(value = "/writeComment.do", method = RequestMethod.POST)
    public final String writeComment(final Integer parentCommentId,
	    @ModelAttribute final ContentComment comment, final Model model) {

	log.info("write comment req. {}", comment);

	boardContentService.writeComment(model, parentCommentId, comment);

	return "success";
    }

    /**
     * Modify comment.
     * 
     * @param comment
     *            comment
     * @param model
     *            model
     * @return viewName
     */
    @RequestMapping(value = "/modifyComment.do", method = RequestMethod.POST)
    public final String modifyComment(
	    @ModelAttribute final ContentComment comment, final Model model) {

	return "success";
    }

    /**
     * Delete comment.
     * 
     * @param commentId commentId
     * @param model model
     * @return viewName
     */
    @RequestMapping(value = "/deleteComment.do", method = RequestMethod.POST)
    public final String deleteComment(final Integer commentId,
	    final Model model) {

	log.info("deleteComment {}.", commentId);

	boardContentService.deleteComment(commentId);

	return "success";
    }
}
