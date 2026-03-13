package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

      private static final ExtentReports extentReports = new ExtentReports();
      private static boolean initialized = false;

      public synchronized static ExtentReports getExtentReports() {
            if (!initialized) {
                  ExtentSparkReporter reporter = new ExtentSparkReporter("report/ExtentReport/ExtentReport.html");
                  reporter.config().setReportName("Extent Report | Anh ");
                  extentReports.attachReporter(reporter);
                  extentReports.setSystemInfo("Framework Name", "Selenium Java | Anh ");
                  extentReports.setSystemInfo("Author", "Anh ");
                  initialized = true;
            }
            return extentReports;
      }

}
