import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static org.junit.jupiter.api.Assertions.*;


public class HomePage extends BasicMethods {

    private String title = "OrangeHRM";

    //elements
    @FindBy(id = "option-menu")
    private WebElement confirmLogin;

    @FindBy(id = "rightMenu")
    private WebElement iframe;

    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    private WebElement logoutButton;

    //constructor
    public HomePage(WebDriver adriver) {
        driver = adriver;
        assertEquals(title, driver.getTitle(), "This is not the Home Page");
    }

    //services
    public LoginPage logout() {
        clickElement(logoutButton);
        return PageFactory.initElements(driver, LoginPage.class);
    }

    public FramePage switchFramePage() {
        driver.switchTo().frame(iframe);
        return PageFactory.initElements(driver, FramePage.class);
    }

    public String getLoginPageConfirmation(){
        return confirmLogin.getText();
    }


}
