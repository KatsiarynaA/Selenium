import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;


public class TestWithPOM extends BasicMethods {

    private WebDriver driver;

    @BeforeEach
    void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    void searchEmployeeName() {
        HomePage homePage = LoginPage.open(driver)
                                     .validLogin("opensourcecms", "opensourcecms");
        assertTrue(homePage.getLoginPageConfirmation().contains("Welcome"));

        FramePage framePage = homePage.switchFramePage()
                                      .searchEmployee("John Lennon");
        assertTrue(framePage.getSearchResult().contains("No Records Found"));

        framePage.logout();
        assertTrue(homePage.getLoginPageConfirmation().contains("Welcome"));

        LoginPage loginPage = homePage.logout();
        assertTrue(loginPage.getLogoutConfirmation().contains("LOGIN Panel"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/ddt.csv", numLinesToSkip = 1)
    void invalidLogin(String username, String password, String expectedErrorMessage) {
        LoginPage loginPage = LoginPage.open(driver)
                                       .invalidLogin(username, password);
        assertTrue(loginPage.getErrorMessage().contains(expectedErrorMessage));
    }

}
