package Pages;

 

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

 

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.ExtentTest;

 

public class ExtentReportListener extends BaseClass implements ITestListener{
    public static ExtentTest test;
     private static ExtentReports extent;
        private static ExtentSparkReporter htmlReporter;

 

        public void onStart(ITestContext context) {
            htmlReporter = new ExtentSparkReporter("test-output/HMSExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }

 

        public void onFinish(ITestContext context) {
            extent.flush();
        }

 

        public void onTestStart(ITestResult result) {
            String testName = result.getMethod().getMethodName();
            test = extent.createTest(result.getTestClass().getRealClass().getSimpleName()+" :"+testName);
            test.log(Status.INFO, "Starting test: " + testName);

        }
        
       

 

        public void onTestSuccess(ITestResult result) {

        }

 

        public void onTestFailure(ITestResult result) {
            String testName = result.getMethod().getMethodName();
            test.log(Status.FAIL, result.getThrowable());


        }

 

        public void onTestSkipped(ITestResult result) {
            String testName = result.getMethod().getMethodName();
            test.log(Status.SKIP, result.getThrowable());


        }

 

        public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        }

 

}