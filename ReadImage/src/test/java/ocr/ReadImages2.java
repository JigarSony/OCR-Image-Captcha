package ocr;

import java.io.File;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ReadImages2 {

	public static void main(String[] args) {
		
		String path = System.getProperty("user.dir");
		System.out.println(path);
		ITesseract image = new Tesseract();
		
		try {
			String str = image.doOCR(new File(path+"/images/banner.png"));
			System.out.println("Data from Image: " + str);
			
			String [] strArray = str.split("below");
			
			System.out.println(strArray[1]);
			
			String data = strArray[1].replaceAll("[^a-zA-Z0-9]", "");
			
			System.out.println(data);
			
		} catch (TesseractException e) {
			System.out.println(e.getMessage());
		}
	}

}
