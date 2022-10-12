package step_def;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.ConfigurationReader;
import utilities.Driver;

import java.time.Duration;

public class Hooks {

WebDriver driver;

    @Before
    public void setUp(Scenario scenario){


        driver= Driver.get();
        System.out.println("BEFORE");
        System.out.println(scenario.getName());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get(ConfigurationReader.get("url"));


    }

    @After
    public void tearDown(Scenario scenario){

        System.out.println("AFTER");
        if(scenario.isFailed()){
            final byte[]screenshot=((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","screenshot");
        }

        Driver.closeDriver();
        System.out.println("Driver Closed");




    }
}
