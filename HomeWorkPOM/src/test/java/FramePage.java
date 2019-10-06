import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static org.junit.jupiter.api.Assertions.*;


public class FramePage extends BasicMethods {

    private String title = "OrangeHRM";

    //elements
    @FindBy(id = "empsearch_employee_name_empName")
    private WebElement employeeName;

    @FindBy(id = "searchBtn")
    private WebElement searchButton;

    @FindBy(xpath = "//table/tbody/tr/td")
    private WebElement searchResult;

    //constructor
    public FramePage(WebDriver adriver) {
        driver = adriver;
        assertEquals(title, driver.getTitle(), "This is not the Frame Page");
    }

    //services
    public HomePage logout() {
        driver.switchTo().defaultContent();
        return PageFactory.initElements(driver, HomePage.class);
    }

    public void search(String name) {
        employeeName.sendKeys(name);
        clickElement(searchButton);
    }

    public FramePage searchEmployee (String name) {
        search(name);
        return this;
    }

    public String getSearchResult() {
        return searchResult.getText();
    }
}
