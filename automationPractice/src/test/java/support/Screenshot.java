package support;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;

public class Screenshot {
    public static void takeScreenshot(WebDriver webD, String file) {
        File ss = ((TakesScreenshot)webD).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(ss, new File(file));
        } catch (Exception e) {
            System.out.println("Could not copy file to folder: " + e.getMessage());
        }
    }
}
