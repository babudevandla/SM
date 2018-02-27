package com.sm.test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import com.github.sardine.DavResource;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;

public class SardineMain {

	private static final String PATH = "http://localhost:8080/webdav/1";

    public static void main(String[] args){
        Sardine sardine = SardineFactory.begin();
        String fileName="cardholder-dispute-form.pdf";
		File file=new File(fileName);
       
        try{
        	//InputStream fis = new FileInputStream(file);
            /*sardine.createDirectory(PATH+"/test/");
            sardine.put(PATH,fis);*/
        	Pattern fileExtnPtrn = Pattern.compile("([^\\s]+(\\.(?i)(txt|doc|csv|pdf))$)");
            List<DavResource> res = sardine.list(PATH);
            for(DavResource dr : res){
                System.out.println(dr.getName());
                System.out.println(dr.getCreation()+"::"+dr.getModified());
                System.out.println(dr.getContentType());
                System.out.println("=================================");
            }
            

            //sardine.delete(PATH+"/"+fileName);
            /*res = sardine.list(PATH);
            for(DavResource dr : res){
                System.out.println(dr);
            }*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
