package com.marcin.stepien.testproject;

import com.marcin.stepien.testproject.utils.Config;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {


    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", Config.chromedriverPath);

        Result result = JUnitCore.runClasses(AllTests.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }
}
