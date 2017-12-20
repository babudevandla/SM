package com.sm.test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;

public class ConvertBase64ToImage {

	public static void main(String[] args) {
		
		String base64String= "BORw0KGgoAAAANSUhEUgAAAUAAAAHgCAYAAADUjLREAAAgAElEQVR4AexdB4BU1dX+ZmZ7ZWGX3pHeu6goitgQDCZGjdHYu4nGqL81mmaJvdd" ;
		
		try{
		// create a buffered image
		BufferedImage image = null;
		byte[] imageByte;

		BASE64Decoder decoder = new BASE64Decoder();
		imageByte = decoder.decodeBuffer(base64String);
		ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
		if (image == null) {
             System.out.println("Buffered Image is null");
         }
		image = ImageIO.read(bis);
		bis.close();

		// write the image to a file
		File outputfile = new File("image.png");
		ImageIO.write(image, "png", outputfile);
	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
