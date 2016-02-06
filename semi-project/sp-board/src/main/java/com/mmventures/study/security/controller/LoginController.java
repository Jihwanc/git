
package com.mmventures.study.security.controller;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MessageSource messageSource;
	
	 @RequestMapping(value = "/login", method = RequestMethod.GET)
		public String login() throws Exception {
			Locale locale = LocaleContextHolder.getLocale();
			
			String testmsg1 = messageSource.getMessage(
					"AbstractLdapAuthenticationProvider.badCredentials", 
					null, "시큐리티 디폴트 메시지", Locale.KOREA);
			
			String testmsg2 = messageSource.getMessage(
					"TestKey.testParam", null, "디폴트 메시지", Locale.KOREA);
			
			logger.debug("testmsg1 : {}", testmsg1);
			logger.debug("testmsg2 : {}", testmsg2);
			return "login";
		}
	 
	 
}
