package com.sm.portal.ebook.service;

import java.util.List;

import com.sm.portal.ebook.model.Ebook;
import com.sm.portal.ebook.model.EbookPageBean;
import com.sm.portal.ebook.model.EbookPageDto;
import com.sm.portal.ebook.model.UserBooks;

public interface EbookService {
	public UserBooks getEbookList(Integer userId);
	public void createUserBook(Ebook ebook);
	void creatChapter(EbookPageBean ebookPageBean);
	void updateChapter(EbookPageBean ebookPageBean);
	void updateEbookPage(EbookPageBean ebookPageBean);
	void updateEbookDetails(Ebook ebook);
	Ebook getEbookContent(Integer userId, Integer bookId);
	void saveEbookPageContent(EbookPageDto eBookPageDto);
}
