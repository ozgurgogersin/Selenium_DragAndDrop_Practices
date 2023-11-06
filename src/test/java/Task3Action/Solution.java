package Task3Action;
import Utilities.BaseDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import java.util.Collections;
import java.util.List;
//TODO 3 : http://dhtmlgoodies.com/scripts/drag-drop-nodes-quiz/drag-drop-nodes-quiz.html
//         Place all of the objects on this page to the correct country

public class Solution extends BaseDriver {
    @Test
    public void DragAndDrop3() {

        driver.get("http://dhtmlgoodies.com/scripts/drag-drop-nodes-quiz/drag-drop-nodes-quiz.html");
        List<WebElement> capitals = driver.findElements(By.xpath("//ul[@id='allItems']//li"));
        List<WebElement> countries = driver.findElements(By.xpath("//div[@id='dhtmlgoodies_mainContainer']//div//ul"));

        System.out.print("Check Trimmed Capital Group Ids: ");
        for (WebElement e : capitals) {
            System.out.print("[" + e.getAttribute("groupid").substring(3) + "] ");
        }
        System.out.println();
        System.out.print("Check Trimmed Country Ids: ");
        for (WebElement c : countries) {
            System.out.print("[" + c.getAttribute("id").substring(3) + "] ");
        }
        Actions actions = new Actions(driver);
        int count = 0;
        for (WebElement eNested : capitals) {
            for (WebElement cNested : countries) {
                if (eNested.getAttribute("groupid").substring(3).equals(cNested.getAttribute("id").substring(3)) && count < capitals.size() - 1) {
                    Action action = actions.clickAndHold(eNested).moveToElement(cNested).release(cNested).build();
                    action.perform();
                    count++;
                }
            }
        }

        Collections.reverse(capitals);
        for (WebElement eNested : capitals) {
            for (WebElement cNested : countries) {
                if (eNested.getAttribute("groupid").substring(3).equals(cNested.getAttribute("id").substring(3))) {
                    Action action = actions.clickAndHold(eNested).build();
                    action.perform();
                    Action action1 = actions.moveToElement(cNested).build();
                    action1.perform();
                    Action action3 = actions.release(cNested).build();
                    action3.perform();
                }
            }
            break;
        }
        driver.switchTo().alert().accept();
        driver.quit();
    }
}