# flink_code_challenge

# Intro
  This repository contains a Selenium web based automation for the Flink QA challenge. The test case that the automation follows can be seen in AutomatedTestCase.md.
  
# Dependecies
- Gradle
- testng:7.4.0
- Java Corretto 11
- assertj-core:3.21.0
- allure-selenide
- Selenide:5.25.0

# Installation
If you are using Intellij IDEA or any of their IDEs, you can install the Selenium UI Testing plugin. This will provide you with an instant way to recognize the dependencies in the project and will be ready to run it by file.


For users that are not using Intellij IDEA, you will need to following a setup similar to the one in this tutorial: https://testingfunda.com/2018/05/08/how-to-configure-selenium-using-gradle-in-eclipse/

Personally, I would recommend downloading Intellij since it's the fastest way to run the project without running into dependency issues.

I worked on the suite in Ubuntu 20.04.3 LTS, 64-bit and tested it in Windows 10 and MacOS before doing the last commit. As for the browsers, inside the browser.json file you can see the versions as well as the local directory where the drivers live, so there is no need to install any drivers or have them in PATH.

# Executing the test cases

You can execute them directly from the IDE of your choice by clicking on "TestRunnerTest" or "ParallelTest". The test cases haven been configured to run in headless mode but this can be changed by removing the line 'onfiguration.headless = true;'
If you want to run them from the terminal, you will need to execute the following: gradle test --tests com.example.flink_qa_challenge.TestRunnerTest


# Review Test Reports
For reviewing the test reports that are generated, you can follow this path: build/reports/index.html


Thanks for taking the time of reviewing the code that I've presented for the challenge. Had a lot of fun coding all of this and would like for us to talk further and discuss it!

Cheers,
Luis
