package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class AssertUtils {
    private static final Logger logger = LogManager.getLogger(AssertUtils.class);
    private static final SoftAssert softAssert = new SoftAssert();

    // ---------- HARD ASSERTIONS ----------
    public static void assertEquals(String actual, String expected, String message) {
        logAssertion("Hard", "Equals", actual, expected, message);
        try {
            Assert.assertEquals(actual, expected, message);
            logger.info("✅ Passed");
        } catch (AssertionError e) {
            logger.error("❌ Failed: " + e.getMessage());
            throw e;
        }
    }

    public static void assertNotEquals(String actual, String expected, String message) {
        logAssertion("Hard", "NotEquals", actual, expected, message);
        try {
            Assert.assertNotEquals(actual, expected, message);
            logger.info("✅ Passed");
        } catch (AssertionError e) {
            logger.error("❌ Failed: " + e.getMessage());
            throw e;
        }
    }

    public static void assertTrue(boolean condition, String message) {
        logAssertion("Hard", "True", String.valueOf(condition), "true", message);
        try {
            Assert.assertTrue(condition, message);
            logger.info("✅ Passed");
        } catch (AssertionError e) {
            logger.error("❌ Failed: " + e.getMessage());
            throw e;
        }
    }

    public static void assertFalse(boolean condition, String message) {
        logAssertion("Hard", "False", String.valueOf(condition), "false", message);
        try {
            Assert.assertFalse(condition, message);
            logger.info("✅ Passed");
        } catch (AssertionError e) {
            logger.error("❌ Failed: " + e.getMessage());
            throw e;
        }
    }

    public static void assertNull(String actual, String message) {
        logAssertion("Hard", "Null", actual, "null", message);
        try {
            Assert.assertNull(actual, message);
            logger.info("✅ Passed");
        } catch (AssertionError e) {
            logger.error("❌ Failed: " + e.getMessage());
            throw e;
        }
    }

    public static void assertNotNull(String actual, String message) {
        logAssertion("Hard", "NotNull", actual, "not null", message);
        try {
            Assert.assertNotNull(actual, message);
            logger.info("✅ Passed");
        } catch (AssertionError e) {
            logger.error("❌ Failed: " + e.getMessage());
            throw e;
        }
    }

    public static void fail(String message) {
        logger.error("❌ Forced Failure: " + message);
        Assert.fail(message);
    }

    // ---------- SOFT ASSERTIONS ----------
    public static void softAssertEquals(String actual, String expected, String message) {
        logAssertion("Soft", "Equals", actual, expected, message);
        softAssert.assertEquals(actual, expected, message);
    }

    public static void softAssertNotEquals(String actual, String expected, String message) {
        logAssertion("Soft", "NotEquals", actual, expected, message);
        softAssert.assertNotEquals(actual, expected, message);
    }

    public static void softAssertTrue(boolean condition, String message) {
        logAssertion("Soft", "True", String.valueOf(condition), "true", message);
        softAssert.assertTrue(condition, message);
    }

    public static void softAssertFalse(boolean condition, String message) {
        logAssertion("Soft", "False", String.valueOf(condition), "false", message);
        softAssert.assertFalse(condition, message);
    }

    public static void softAssertNull(String actual, String message) {
        logAssertion("Soft", "Null", actual, "null", message);
        softAssert.assertNull(actual, message);
    }

    public static void softAssertNotNull(String actual, String message) {
        logAssertion("Soft", "NotNull", actual, "not null", message);
        softAssert.assertNotNull(actual, message);
    }

    public static void softFail(String message) {
        logger.error("❌ Soft Forced Failure: " + message);
        softAssert.fail(message);
    }

    public static void assertAll() {
        logger.info("🔍 Verifying all soft assertions...");
        softAssert.assertAll();
    }

    // ---------- INTERNAL LOGGING ----------
    private static void logAssertion(String type, String method, String actual, String expected, String message) {
        logger.info(type + " Assert " + method + ": " + message);
        logger.info("Expected: " + expected);
        logger.info("Actual: " + actual);
    }
}
