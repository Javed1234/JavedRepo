package com.java.springmvc.dao;

import java.util.List;

import com.java.springmvc.model.Contact;

public interface InterfaceContact {
	public void saveOrUpdate(Contact contact);

	public void delete(int contactId);

	public Contact get(int contactId);

	public List<Contact> list();

}
