package roughWorkPackage;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Base64;

public class Demo {
    RemoteWebDriver driver;

    @Test
    @Parameters({"username", "password", "browser"})
    void login(String uname, String pass, String browser) {
        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                System.err.println("browser is not defined!");
                break;
        }
    }

    @DataProvider(name = "login", indices = {0, 2}, parallel = true)
    public Object[][] dataSupplier() {
        String[][] data = new String[2][2];
        data[0][0] = "abc@gmail.com";
        data[0][1] = "password1";
        data[1][0] = "xyz@gmail.com";
        data[1][1] = "password2";
        return data;
    }

    @Test(dataProvider = "login", dataProviderClass = Demo.class)
    void login2(String username, String password) throws IOException {

        String fileLocation = "./resources/data.xlsx";
        XSSFWorkbook workbook = new XSSFWorkbook(fileLocation);
        XSSFSheet sheet = workbook.getSheet("sheet1");
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < 2; j++) {
                XSSFCell cell = row.getCell(j);
                DataFormatter dataFormatter = new DataFormatter();
                String value = dataFormatter.formatCellValue(cell);
//                String value = cell.getStringCellValue();
                System.out.println(value);
            }
        }
        workbook.close();
    }

    public static String getDecodedString(String encodedString) {
        return new String(Base64.getDecoder().decode(encodedString.getBytes()));
    }

}