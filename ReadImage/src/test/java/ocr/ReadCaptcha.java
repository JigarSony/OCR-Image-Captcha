package ocr;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ReadCaptcha {

	public static void main(String[] args) {
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notification");

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(opt);
		driver.manage().window().maximize();

		String path = System.getProperty("user.dir") + "/screenshot/capcha.png";

		driver.get("https://www.irctc.co.in/nget/train-search");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.findElement(By.id("loginText")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		File src = driver.findElement(By.id("captchaImg")).getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(src, new File(path));
		} catch (IOException e) {
			// System.out.println(e.getMessage());
		}
		ITesseract image = new Tesseract();

		try {
			String str = image.doOCR(new File(path));
			System.out.println("Data from Image: " + str);

			String[] strArray = str.split("below");

			System.out.println(strArray[1]);

			String data = strArray[1].replaceAll("[^a-zA-Z0-9]", "");

			System.out.println(data);
			driver.findElement(By.id("nlpAnswer")).sendKeys(data);
		} catch (TesseractException e) {
			System.out.println(e.getMessage());
		}

	}

}
