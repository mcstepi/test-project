package com.marcin.stepien.testproject;

import com.marcin.stepien.testproject.test.AddUserTest;
import com.marcin.stepien.testproject.utils.Config;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        AddUserTest.class})

public class AllTests {

}