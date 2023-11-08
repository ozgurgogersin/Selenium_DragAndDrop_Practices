package Task2Action;

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

//TODO 2 : http://dhtmlgoodies.com/scripts/drag-drop-nodes/drag-drop-nodes.html
//          Fill the team boxes with the students (Hint: Use the same method with Q1)
public class Solution_1 extends BaseDriver {
    @Test
    public void DragAndDrop2() {
        driver.get("http://dhtmlgoodies.com/scripts/drag-drop-nodes/drag-drop-nodes.html");

        List<WebElement> students = driver.findElements(By.xpath("//div[@id='dhtmlgoodies_dragDropContainer']//div[@id='dhtmlgoodies_listOfItems']//div//ul[@id='allItems']//li"));
        List<WebElement> boxes = driver.findElements(By.xpath("//div[@id='dhtmlgoodies_dragDropContainer']//div[@id='dhtmlgoodies_mainContainer']//div//ul"));

        Actions actions = new Actions(driver);

        int counter = 0;
        for (int i = 0; i < students.size(); i++) {
            Action action = actions.clickAndHold(students.get(i)).build();
            action.perform();
            MyMethods.myWait(1);
            Action action1 = actions.moveToElement(boxes.get(counter)).build();
            action1.perform();
            Action action2 = actions.release().build();
            action2.perform();
            counter++;
            if (counter == boxes.size()) {
                counter = 0;
            }
        }

        if (students.size() > 0) {
            Action action4 = actions.clickAndHold(students.get(students.size() - 1)).build();
            action4.perform();
            MyMethods.myWait(1);
            Action action5 = actions.moveToElement(boxes.get(boxes.size() - 1)).build();
            action5.perform();
            Action action6 = actions.release().build();
            action6.perform();
        }
        WebElement saveButton = driver.findElement(By.xpath("//input[@type='button']"));
        saveButton.click();
        WebElement saveContent = driver.findElement(By.xpath("//div[@id='saveContent']"));
        Assert.assertTrue("Test Failed!", saveContent.isDisplayed());
        driver.quit();
    }
}

