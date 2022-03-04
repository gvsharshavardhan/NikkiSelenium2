package letcode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SimpleTablePage extends BasePage {

    @Test
    public void shoppingListTableTest() {
        jsScrollAlongYAxisBy(2000);
        click(By.xpath(letCodeProp.getProperty("letcode.tabs.xpath")
                .replace("$$", "Simple table")));
        String shoppingListPricesXpath = "//table[@id='shopping']/tbody//td[2]";
        List<WebElement> shoppingListPricesElements = driver.findElements(By.xpath(shoppingListPricesXpath));
        int sum = 0;
        for (WebElement ele : shoppingListPricesElements) {
            sum = sum + Integer.parseInt(ele.getText().trim());
        }
        int total = Integer.parseInt(driver.findElement(By.xpath("//table[@id='shopping']/tfoot//b")).getText().trim());
        Assert.assertEquals(sum, total, "sum of prices is not matching with the total!!");
    }

    @Test
    public void attendanceTableTest() {
        jsScrollAlongYAxisBy(2000);
        click(By.xpath(letCodeProp.getProperty("letcode.tabs.xpath")
                .replace("$$", "Simple table")));
        String name = "Raj";
        getElement(By.xpath("//table[@id='simpletable']//td[.='" + name + "']/following-sibling::td/input")).click();
        boolean actual = getElement(By.xpath("//table[@id='simpletable']//td[.='" + name + "']/following-sibling::td/input")).isSelected();
        Assert.assertTrue(actual, "checkbox should be checked!! not working might be!");
    }

    @Test
    public void sortableTableTest() {
        String nutritionValue = "Cholesterol";
        String headersXpath = "//*[.='Sortable Tables']/following-sibling::table//th//div[contains(@class,'mat-sort-header-content')]";
        String headerXpath = "//*[.='Sortable Tables']/following-sibling::table//div[contains(@class,'mat-sort-header-content')  and contains(.,'$$')]/ancestor::th";


        jsScrollAlongYAxisBy(2000);
        click(By.xpath(letCodeProp.getProperty("letcode.tabs.xpath")
                .replace("$$", "Simple table")));
        List<WebElement> headerElements = driver.findElements(By.xpath(headersXpath));
        List<String> headerNames = new ArrayList<>();
        for (WebElement header : headerElements) {
            headerNames.add(header.getText().trim().split("\\(")[0]);
        }

        jsScrollAlongYAxisBy(500);
        //ascending logic
        click(By.xpath(headerXpath.replace("$$", nutritionValue)));
        takeRestForHalfSecond();
        String expectedSortOrder = driver.findElement(By.xpath(headerXpath.replace("$$", nutritionValue))).getAttribute("aria-sort");
        String index = String.valueOf(headerNames.indexOf(nutritionValue));
        List<String> values = getValuesFromSortableTable(index);
        boolean actualSortOrder = isListSorted(expectedSortOrder, values);

        Assert.assertTrue(actualSortOrder, "sorting is working as expected!! " + expectedSortOrder + " in specific!");

        //descending logic
        click(By.xpath(headerXpath.replace("$$", nutritionValue)));
        takeRestForHalfSecond();
        expectedSortOrder = driver.findElement(By.xpath(headerXpath.replace("$$", nutritionValue))).getAttribute("aria-sort");
        values = getValuesFromSortableTable(index);
        actualSortOrder = isListSorted(expectedSortOrder, values);

        Assert.assertTrue(actualSortOrder, "sorting is working as expected!! " + expectedSortOrder + " in specific!");
    }

    public boolean isListSorted(String sort, List<String> values) {
        boolean status = true;
        for (int i = 0; i < values.size() - 1; i++) {
            if (sort.equals("ascending")) {
                if (values.get(i).compareTo(values.get(i + 1)) > 0) {
                    status = false;
                    break;
                }
            } else {
                if (values.get(i).compareTo(values.get(i + 1)) < 0) {
                    status = false;
                    break;
                }
            }
        }
        return status;
    }

    public List<String> getValuesFromSortableTable(String index) {
        String columnValues = "//*[.='Sortable Tables']/following-sibling::table//td[$$]";
        List<WebElement> valueElements = driver.findElements(By.xpath(columnValues.replace("$$", index)));
        List<String> values = new ArrayList<>();
        for (WebElement ele : valueElements) {
            values.add(ele.getText().trim());
        }
        return values;
    }
}