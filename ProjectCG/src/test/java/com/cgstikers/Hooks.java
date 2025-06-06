package com.cgstikers;

import com.cgstikers.ui.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    @Before("@ui")
    public void beforeScenario() {
        DriverManager.initDriver();
    }

    @After("@ui")
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            //takescreenshot
        }
//        DriverManager.quitDriver();
    }
}
