package com.java.springmvc.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.java.springmvc.dao.InterfaceContact;
import com.java.springmvc.model.Contact;

@Controller
public class HomeController {
	@Autowired
	private InterfaceContact contact;

	@RequestMapping(value = "/")
	public ModelAndView listContact(ModelAndView andView) {
		List<Contact> list = contact.list();
		andView.addObject("listContact", list);
		andView.setViewName("home");
		return andView;
	}

	@RequestMapping(value = "/newContact", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView andView) {
		Contact contact = new Contact();
		andView.addObject("contact", contact);
		andView.setViewName("ContactForm");
		return andView;
	}

	@RequestMapping(value = "/saveContact", method = RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute Contact contacts) {
		contact.saveOrUpdate(contacts);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
	public ModelAndView deleteContact(HttpServletRequest httpServletRequest) {
		int contactid = Integer.parseInt(httpServletRequest.getParameter("id"));
		contact.delete(contactid);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/editContact", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest httpServletRequest) {
		int contactId = Integer.parseInt(httpServletRequest.getParameter("id"));
		Contact contacts = contact.get(contactId);
		ModelAndView modelAndView = new ModelAndView("ContactForm");
		modelAndView.addObject("contact", contacts);
		return modelAndView;
	}

}
