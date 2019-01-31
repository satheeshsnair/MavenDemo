package functionLibrary;

import org.testng.Reporter;

import com.aventstack.extentreports.Status;

import junit.framework.AssertionFailedError;

public class ErrorHandling extends sharedFunctions {

	public void assertEquals(Object expected, Object actual, String message) {
		if (expected == null && actual == null) {
			testInstance.log(Status.FAIL, "Expected and Actual values are NULL");
			Reporter.log("Expected and Actual values are NULL", false);
			extentInstance.setTestRunnerOutput("Expected and Actual values are NULL");
			return;
		}
		if (expected != null && expected.equals(actual)) {
			testInstance.log(Status.PASS, message);
			Reporter.log(message);
			extentInstance.setTestRunnerOutput(message);
			return;
		} else {
			Reporter.log("Expected Result: '" + expected + "' does NOT match with Actual Result: '" + actual + "' .");
			extentInstance.setTestRunnerOutput(
					"Expected Result: '" + expected + "' does NOT match with Actual Result: '" + actual + "' .");
			Reporter.log("Error Snapshot is Located in : " + Const.ErrorReportPath + Captialize(runOnBrowser) + "_"
					+ executingTestCaseName + Const.DateFormat + ".jpg");
			fail(format("", expected, actual));
		}
	}

	public void fail(String message) {
		if (message == null) {
			throw new AssertionFailedError();
		}
		throw new AssertionFailedError(message);
	}

	public String format(String message, Object expected, Object actual) {
		String formatted = "The Expected Outcome is not matched with Actual Outcome::";
		if (message != null && message.length() > 0) {
			formatted = message + "  ";
		}
		return formatted + "Expected :'" + expected + "' but Actual is:'" + actual + "'";
	}

}
