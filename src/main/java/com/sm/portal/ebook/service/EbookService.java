package com.sm.portal.ebook.service;

import java.util.List;

import com.sm.portal.ebook.model.Ebook;
import com.sm.portal.ebook.model.EbookPageBean;

public interface EbookService {
	public List<Ebook> getEbookList(Integer userId);
	public void createEbook(Ebook ebook);
	void creatChapter(EbookPageBean ebookPageBean);
	void updateChapter(EbookPageBean ebookPageBean);
	void updateEbookPage(EbookPageBean ebookPageBean);
	void updateEbookDetails(Ebook ebook);
}
