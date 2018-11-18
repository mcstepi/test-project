package com.marcin.stepien.testproject;

import com.marcin.stepien.testproject.test.AddUserTest;
import com.marcin.stepien.testproject.test.AllUsersTest;
import com.marcin.stepien.testproject.test.UserApiTest;
import com.marcin.stepien.testproject.utils.CheckOS;
import com.marcin.stepien.testproject.utils.Config;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        AddUserTest.class,
        AllUsersTest.class,
        UserApiTest.class
        })

public class AllTestsRunner {

    @BeforeClass
    public static void init() {
        String platform = CheckOS.getOsName();
        Config.chromedriverPath = String.format(Config.chromedriverPath, platform);
        System.setProperty("webdriver.chrome.driver", Config.chromedriverPath);
    }
}