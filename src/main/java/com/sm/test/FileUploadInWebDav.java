package com.sm.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystemException;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.impl.DefaultFileSystemManager;
import org.apache.commons.vfs2.provider.local.DefaultLocalFileProvider;
import org.apache.commons.vfs2.provider.webdav.WebdavFileProvider;

import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;

public class FileUploadInWebDav {

	static String BASE_URL="http://localhost:9667/webdav";
	public static void main(String[] args) {
		
		String fileName="D:\\app\\caweb_2016\\invoice\\88694930_1627286.pdf";
		File file=new File(fileName);
		try {
			//saveFileToDefaultFileSystem(file);
			
			
			Sardine sardine = SardineFactory.begin();
			InputStream fis = new FileInputStream(file);
			sardine.put(BASE_URL, fis);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static String saveFileToDefaultFileSystem(File file) throws FileSystemException, IOException {
		
		String fileName;
		DefaultFileSystemManager fsManager;

		try {
			fsManager = new DefaultFileSystemManager();
			fsManager.addProvider("webdav", new WebdavFileProvider());
			fsManager.addProvider("file", new DefaultLocalFileProvider());
			fsManager.init();
		} catch (org.apache.commons.vfs2.FileSystemException e) {
			throw new FileSystemException("Exception initializing DefaultFileSystemManager: " + e.getMessage());
		}
		
		UUID uuid = UUID.randomUUID();
		fileName = uuid.toString();
		
		FileObject uploadedFile;
		FileObject destinationFile;
		
		try {
			uploadedFile = fsManager.toFileObject(file);
			destinationFile = fsManager.resolveFile(BASE_URL + fileName);
			destinationFile.createFile();
		} catch (org.apache.commons.vfs2.FileSystemException e) {
			fsManager.close();
			throw new FileSystemException("Exception resolving file in file store: " + e.getMessage());
		}
		
		try (InputStream in = uploadedFile.getContent().getInputStream();
			 OutputStream out = destinationFile.getContent().getOutputStream()) {
			
			IOUtils.copy(in, out);
			} catch (IOException e) {
			throw new IOException("Exception copying data: " + e.getMessage());
		} finally {
			fsManager.close();
		}
		
		return fileName;
			
	}
}
