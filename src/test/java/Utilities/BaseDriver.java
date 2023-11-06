package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.IOException;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDriver {
    public static WebDriver driver;

    static {
        closePreviousDrivers();
        Logger logger = Logger.getLogger("");//shows only severe problems on console
        logger.setLevel(Level.SEVERE);
        driver = new EdgeDriver();
        driver.manage().window().maximize();//fits the browser to the screen
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        //Waits for 15 sec for page to be loaded.
        // if it takes more than 15 sec to a page being loaded it throws an exception.
        //PageLoad=waits all html files are downloaded to our computer, but they are not
        //visible on our browser yet.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        //implicitlyWait: waits until all elements on a webpage are visible on our browser.
        //İf it's not done in 15 secs it throws an exception.
    }
    public static void closePreviousDrivers() {
        try {
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
