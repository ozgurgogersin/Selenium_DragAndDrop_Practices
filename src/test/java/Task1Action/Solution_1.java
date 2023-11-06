package Task1Action;

import Utilities.BaseDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
//  TODO 1 : http://dhtmlgoodies.com/scripts/drag-drop-quiz/drag-drop-quiz-d2.html
//         Put all cities in the correct contries. (Hint: Use findElements and a loop for both cities and countries)
public class Solution_1 extends BaseDriver {
    @Test
    public void DragAndDrop1() {
        driver.get("http://dhtmlgoodies.com/scripts/drag-drop-quiz/drag-drop-quiz-d2.html");

        List<WebElement> capitals = driver.findElements(By.xpath("//div[@id='answerDiv']//div"));
        List<WebElement> countries = driver.findElements(By.xpath("//div[@id='questionDiv']//div[@class='dragDropSmallBox']"));
        Actions actions = new Actions(driver);

        for (WebElement e : capitals) {
            for (WebElement c : countries) {

                Action action = actions.clickAndHold(e).moveToElement(c).release(c).build();
                action.perform();

                if (e.getCssValue("background-color").equals("rgba(0, 128, 0, 1)")) {
                    countries.remove(c);
                    break;
                }
            }
        }
        driver.quit();
    }

}

