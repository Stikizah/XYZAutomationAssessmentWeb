package operation;
import static org.testng.Assert.assertEquals;

import java.util.Properties;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.ExtentTest;
public class UIOperation {
    WebDriver driver;
    public UIOperation(WebDriver driver){
        this.driver = driver;
    }
    private StringBuffer verificationErrors = new StringBuffer();
    static com.aventstack.extentreports.ExtentTest test;
    
    public void perform(String operation,String objectName,String objectType,String value) throws Exception{
    	// start reporters
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
    
        // create ExtentReports and attach reporter(s)
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
     // creates a toggle for the given test, adds all log events under it    
        com.aventstack.extentreports.ExtentTest test = extent.createTest("MyFirstTest", "Sample description");
        
        System.out.println("");
        switch (operation.toUpperCase()) {
            case "CLICK":
                //Perform click
                driver.findElement(this.getObject(objectName,objectType)).click();
             // log(Status, details)
                test.log(Status.PASS, objectName);
                // info(details)
                test.info("This step shows usage of info(details)" +objectName);
                // log with snapshot
                test.pass(objectName, MediaEntityBuilder.createScreenCaptureFromPath(objectName+".png").build());
                // test with snapshot
                test.addScreenCaptureFromPath(objectName+".png");
                // calling flush writes everything to the log file
                extent.flush();

                break;
            case "SETTEXT":
                //Set text on control
            	driver.findElement(this.getObject(objectName, objectType)).clear();
                driver.findElement(this.getObject(objectName,objectType)).sendKeys(value);
                
             // log(Status, details)
                test.log(Status.PASS, objectName);
                // info(details)
                test.info("This step shows usage of info(details)" +objectName);
                // log with snapshot
                test.pass(objectName, MediaEntityBuilder.createScreenCaptureFromPath(objectName+".png").build());
                // test with snapshot
                test.addScreenCaptureFromPath(objectName+".png");
                // calling flush writes everything to the log file
                extent.flush();
                
                break;

            case "GOTOURL":
                //Get url of application
                driver.get(value);
                
             // log(Status, details)
                test.log(Status.PASS, value);
                // info(details)
                test.info("This step shows usage of info(details)" +value);
                // log with snapshot
                test.pass(value, MediaEntityBuilder.createScreenCaptureFromPath(objectName+".png").build());
                // test with snapshot
                test.addScreenCaptureFromPath(objectName+".png");
                // calling flush writes everything to the log file
                extent.flush();
                break;
            case "GETTEXT":
                //Get text of an element
                driver.findElement(this.getObject(objectName,objectType)).getText();
                
             // log(Status, details)
                test.log(Status.PASS, objectName);
                // info(details)
                test.info("This step shows usage of info(details)" +objectName);
                // log with snapshot
                test.pass(objectName, MediaEntityBuilder.createScreenCaptureFromPath(objectName+".png").build());
                // test with snapshot
                test.addScreenCaptureFromPath(objectName+".png");
                // calling flush writes everything to the log file
                extent.flush();
                
                break;
            
            case "SELECTVALUE":
                //Select from dropdown
            	Select drpName = new Select(driver.findElement(this.getObject(objectName, objectType)));
            	drpName.selectByVisibleText(value);
            	
            	// log(Status, details)
                test.log(Status.PASS, objectName);
                // info(details)
                test.info("This step shows usage of info(details)" +objectName);
                // log with snapshot
                test.pass(objectName, MediaEntityBuilder.createScreenCaptureFromPath(objectName+".png").build());
                // test with snapshot
                test.addScreenCaptureFromPath(objectName+".png");
                // calling flush writes everything to the log file
                extent.flush();
                break;
                
            case "WAITFORELEMENT":
            	//Wait for element to display
            	driver.findElement(this.getObject(objectName,objectType)).isDisplayed();
            	
            	// log(Status, details)
                test.log(Status.PASS, objectName);
                // info(details)
                test.info("This step shows usage of info(details)" +objectName);
                // log with snapshot
                test.pass(objectName, MediaEntityBuilder.createScreenCaptureFromPath(objectName+".png").build());
                // test with snapshot
                test.addScreenCaptureFromPath(objectName+".png");
                // calling flush writes everything to the log file
                extent.flush();
                break;
                
            	//validate and compare
            case "VALIDATEANDCOMPARE":
            	try {
      		      assertEquals(driver.findElement(this.getObject(objectName, objectType)).getText(), value);//ResultValue
      		    } catch (Error e) {
      		      verificationErrors.append(e.toString());
      		      
      		    }
            
            
            default:
                break;
        }
    }

    /**
     * Find element BY using object type and value
     * @param p
     * @param objectName
     * @param objectType
     * @return
     * @throws Exception
     */
    private By getObject(String objectName,String objectType) throws Exception{
        //Find by xpath
        if(objectType.equalsIgnoreCase("XPATH")){

            return By.xpath((objectName));
        }
        //find by class
        else if(objectType.equalsIgnoreCase("CLASSNAME")){

            return By.className((objectName));

        }
      //find by ID
        else if(objectType.equalsIgnoreCase("ID")){

            return By.id((objectName));

        }
        //find by name
        else if(objectType.equalsIgnoreCase("NAME")){

            return By.name(objectName);

        }
        //Find by css
        else if(objectType.equalsIgnoreCase("CSS")){

            return By.cssSelector(objectName);

        }
        //find by link
        else if(objectType.equalsIgnoreCase("LINK")){

            return By.linkText(objectName);

        }
        //find by partial link
        else if(objectType.equalsIgnoreCase("PARTIALLINK")){

            return By.partialLinkText(objectName);

        }else
        {
            throw new Exception("Wrong object type");
        }
    }

    public void OpenBrowser()
    {

    }
}