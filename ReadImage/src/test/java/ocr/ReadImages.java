package ocr;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ReadImages {

	public static void main(String[] args) {
		
		String path = System.getProperty("user.dir");
		System.out.println(path);
		ITesseract image = new Tesseract();
		
		try {
			String str = image.doOCR(new File("/Users/jigarsony/Automation/WorkSpace/ReadImage/images/download.png"));
			System.out.println("Data from Image" + str);
		} catch (TesseractException e) {
			System.out.println(e.getMessage());
		}
	}

}
