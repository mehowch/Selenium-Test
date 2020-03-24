import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestSelenium {

    DataFormatter dataFormatter = new DataFormatter();
    WebDriver driver;
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    public static void main(String[] args) throws InterruptedException {

        TestSelenium obj = new TestSelenium();

        obj.setExcelFileSheet();
        obj.openBrowser();

        sheet = workbook.getSheetAt(0);
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row currentRow = sheet.getRow(i);
            for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {
                Cell currentCell = currentRow.getCell(j);
                System.out.print(currentCell.getStringCellValue() + "\t" + "\n");

                obj.search(currentCell.getStringCellValue());
                Thread.sleep(3000);
            }
        }
    }

    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "D:\\Shiz\\Programming\\selenium-java-3.141.59\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.amazon.com/");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    private void search(String searchValue) {
//        String value = getCellsData();
        Select select = new Select(driver.findElement(By.xpath("//*[@id=\"searchDropdownBox\"]")));
        select.selectByVisibleText("Sports & Outdoors");
        driver.findElement(By.xpath("//*[@id=\"searchDropdownBox\"]/option[25]")).click();
        driver.findElement(By.cssSelector("#twotabsearchtextbox")).clear();
        driver.findElement(By.cssSelector("#twotabsearchtextbox")).sendKeys(searchValue);
        driver.findElement(By.cssSelector(".nav-input")).click();
//        driver.findElement(By.xpath("//*[@id=\"a-autoid-0-announce\"]")).click();
//        driver.findElement(By.xpath("//*[@id=\"s-result-sort-select_2\"]")).click();
//        driver.findElement(By.linkText("Price: High to Low")).click();
    }

    private void setExcelFileSheet() {
        try {
            workbook = new XSSFWorkbook(new FileInputStream
                    ("D:\\Shiz\\Programming\\Selenium-Test\\src\\main\\resources\\Zeszyt1.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        workbook.getSheet("Arkusz1");
    }
}