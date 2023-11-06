package Task2Action;
import Utilities.BaseDriver;
import Utilities.MyMethods;
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
        List<WebElement>boxesInside=driver.findElements(By.xpath("//div[@id='dhtmlgoodies_dragDropContainer']//div[@id='dhtmlgoodies_mainContainer']//div//li"));

        List<String> studentIds = new ArrayList<>();
        for (WebElement studentList : students) {
            studentIds.add(studentList.getAttribute("id"));
            System.out.println(studentIds.size());
        }
        List<String> boxIds = new ArrayList<>();
        for (WebElement boxList : boxes) {
            boxIds.add(boxList.getAttribute("id"));
            for (WebElement insideBoxList:boxesInside){
                boxIds.add(insideBoxList.getAttribute("id"));
            }
        }
        Actions actions = new Actions(driver);
        for (int i = 0; i < students.size(); i++) {
            for (int j = 0; j < boxes.size(); j++) {
                if (studentIds.get(j).substring(4).equals(boxIds.get(i).substring(3))) {
                    Action action = actions.clickAndHold(students.get(j)).build();
                    action.perform();
                    MyMethods.myWait(1);
                    Action action1 = actions.moveToElement(boxes.get(i)).build();
                    action1.perform();
                    MyMethods.myWait(1);
                    Action action2 = actions.release().build();
                    action2.perform();
                    MyMethods.myWait(1);
                }
            }
        }
        driver.quit();
    }
}
