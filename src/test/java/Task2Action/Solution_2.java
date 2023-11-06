/*
2 : http://dhtmlgoodies.com/scripts/drag-drop-nodes/drag-drop-nodes.html
          Fill the team boxes with the students (Hint: Use the same method with Q1)

 */
package Task2Action;

import Utilities.BaseDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.Collections;
import java.util.List;

public class Solution_2 extends BaseDriver {
    @Test
    public void DragAndDrop2() {
        driver.get("http://dhtmlgoodies.com/scripts/drag-drop-nodes/drag-drop-nodes.html");

        List<WebElement> students = driver.findElements(By.xpath("//div[@id='dhtmlgoodies_dragDropContainer']//div[@id='dhtmlgoodies_listOfItems']//div//ul[@id='allItems']//li"));
        List<WebElement> boxes = driver.findElements(By.xpath("//div[@id='dhtmlgoodies_dragDropContainer']//div[@id='dhtmlgoodies_mainContainer']//div//ul"));

        Actions actions = new Actions(driver);
        Collections.reverse(boxes);
        for (WebElement e : students) {
            for (WebElement c : boxes) {
                Action action = actions.clickAndHold(e).moveToElement(c).release(c).build();
                action.perform();
            }
        }
        WebElement saveButton = driver.findElement(By.xpath("//input[@type='button']"));
        saveButton.click();
        WebElement saveContent = driver.findElement(By.xpath("//div[@id='saveContent']"));
        Assert.assertTrue("Test Failed!", saveContent.isDisplayed());
        driver.quit();
    }
}




