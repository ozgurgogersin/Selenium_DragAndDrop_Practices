package Task1Action;

import Utilities.BaseDriver;
import Utilities.MyMethods;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class Solution_2 extends BaseDriver {
    @Test
    public void DragAndDrop1() {
        driver.get("http://dhtmlgoodies.com/scripts/drag-drop-quiz/drag-drop-quiz-d2.html");

        List<WebElement> countries = driver.findElements(By.xpath("//div[@id='questionDiv']//div[@class='dragDropSmallBox']"));
        List<WebElement> capitals = driver.findElements(By.xpath("//div[@id='answerDiv']//div"));

        List<String> countryIds = new ArrayList<>();
        for (WebElement listCountries : countries) {
            countryIds.add(listCountries.getAttribute("id"));
        }

        List<String> capitalIds = new ArrayList<>();
        for (WebElement capitalsList : capitals) {
            capitalIds.add(capitalsList.getAttribute("id"));
        }

        Actions actions = new Actions(driver);
        for (int i = 0; i < capitals.size(); i++) {
            for (int j = 0; j < countries.size(); j++) {
                if (capitalIds.get(j).substring(1).equals(countryIds.get(i).substring(1))) {
                    Action action = actions.clickAndHold(capitals.get(j)).build();
                    action.perform();

                    MyMethods.myWait((1));
                    Action action1 = actions.moveToElement(countries.get(i)).build();
                    action1.perform();

                    Action action2 = actions.release().build();
                    action2.perform();
                }
            }
        }
        WebElement dropBoxColor = driver.findElement(By.xpath("//div[@id='dragScriptContainer']//div[@id='questionDiv']//div[@id='q6']//following-sibling::div[@class='destinationBox']//div"));
        Assert.assertTrue(dropBoxColor.getCssValue("background-color").equals("rgba(0, 128, 0, 1)"));
        driver.quit();
    }
}

