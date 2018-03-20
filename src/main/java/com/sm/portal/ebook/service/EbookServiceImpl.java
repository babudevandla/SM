package com.sm.portal.ebook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.portal.ebook.model.Ebook;
import com.sm.portal.ebook.model.EbookPage;
import com.sm.portal.ebook.model.EbookPageBean;
import com.sm.portal.ebook.model.EbookPageDto;
import com.sm.portal.ebook.model.UserBooks;
import com.sm.portal.ebook.mongo.dao.EbookMongoDao;

@Service
public class EbookServiceImpl implements EbookService{

	@Autowired
	public EbookMongoDao ebookMongoDao;
	@Override
	public UserBooks getEbookList(Integer userId) {

		UserBooks userBooks = ebookMongoDao.getEbookList(userId);
		return userBooks;
	}

	@Override
	public void createUserBook(Ebook ebook) {

		ebookMongoDao.createUserBook(ebook);
	}
	@Override
	public void creatChapter(EbookPageBean ebookPageBean) {

		ebookMongoDao.creatChapter(ebookPageBean);
	}
	@Override
	public void updateChapter(EbookPageBean ebookPageBean) {

		ebookMongoDao.updateChapter(ebookPageBean);
	}
	@Override
	public void updateEbookPage(EbookPageBean ebookPageBean) {

		ebookMongoDao.updateEbookPage(ebookPageBean);
	}
	@Override
	public void updateEbookDetails(Ebook ebook) {

		ebookMongoDao.updateEbookDetails(ebook);
	}

	@Override
	public Ebook getEbookContent(Integer userId, Integer bookId) {

		Ebook ebook=ebookMongoDao.getEbookContent(userId,bookId );
		EbookPage ep=ebook.getEbookPages().get(0);
		ebook.setDefaultPage(ep);
		return ebook;
	}//getEbookContent() closing
	@Override
	public void saveEbookPageContent(EbookPageDto eBookPageDto) {

		ebookMongoDao.saveEbookPageContent(eBookPageDto);
		
	}//saveEbookPageContent() closing
	
	
	
}//class closing
