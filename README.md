
VF_CZ_JAVA_SELENIUM

This project is a Selenium-based Java automation framework for testing the Vodafone Czech Republic website. It includes features for adding items to the shopping cart and validating the cart's functionality.

Project Structure
```bash
VF_CZ_JAVA_SELENIUM
├── .idea                   # Project settings for IntelliJ IDEA
├── allure-results          # Directory to store Allure report results
├── drivers
│   └── chromedriver.exe    # ChromeDriver executable for running Selenium tests
├── src
│   ├── main
│   │   ├── java            # Main Java codebase (currently empty)
│   │   └── resources       # Resources for the project
│   └── test
│       └── java
│           └── BasketTest  # Java test class for basket functionality
├── target                  # Directory for compiled output files
├── .gitignore              # Git ignore file
├── pom.xml                 # Maven configuration file
└── testng.xml              # TestNG configuration file
```
Project Components

- Allure Reporting: This project utilizes Allure for generating comprehensive test reports. The allure-results folder contains results that can be used to generate reports.

- Selenium WebDriver: The project uses Selenium WebDriver to interact with the Chrome browser. Ensure that chromedriver.exe is located in the drivers folder.

- TestNG: The project is built on the TestNG framework for structuring and executing tests. The testng.xml file defines the test suite configuration.

Prerequisites

1. Java: Make sure you have JDK 11 or higher installed.
2. Maven: Apache Maven is required to build and run the project.
3. ChromeDriver: The appropriate version of ChromeDriver must be available in the drivers folder.
4. Allure: To generate reports, Allure must be installed on your system.

Getting Started

1. Clone the repository:
   git clone https://github.com/yourusername/VF_CZ_JAVA_SELENIUM.git

2. Navigate to the project directory:
   cd VF_CZ_JAVA_SELENIUM

3. Run tests using Maven:
   mvn clean test

4. Generate an Allure report (if Allure is installed):
   allure serve allure-results

Test Description

The main test in this project is BasketTest, which contains a test case to add an iPhone to the shopping basket on the Vodafone Czech Republic website.

Key Test Steps:

1. Open the Vodafone Czech Republic website.
2. Accept cookies.
3. Navigate to the "Obchod" section and select the iPhone category.
4. Add the selected iPhone to the shopping basket.
5. Validate that the item was successfully added to the cart.

Configuration

- testng.xml: Contains the TestNG suite configuration, defining which tests to run.
- pom.xml: Maven POM file to manage dependencies and build configurations.

Dependencies

The main dependencies are:

- Selenium WebDriver: This is used to interact with the web browser.
- TestNG: For structuring tests.
- Allure: For generating test reports.

These dependencies are managed in pom.xml.

## Run

1. from tstNG file testng.xml.
2. Right Click Run.
License

## Using Allure for Generating Test Reports

Allure is a flexible and lightweight framework for creating test reports. This section explains how to set up and use Allure with your project.

### Prerequisites
1. Install **Java** (Java 8 or higher).
2. Ensure **Maven** or **Gradle** is installed (depending on your build system).
3. Install the **Allure Commandline** tool for Windows:
   - Download the latest Allure Commandline release from the [official Allure website](https://docs.qameta.io/allure/).
   - Extract the downloaded file to a desired location (e.g., `C:\Allure`).
   - Add the extracted `bin` folder to your system's `PATH` environment variable.

---

### Maven Configuration
1. Add the Allure dependency in your `pom.xml`:
   ```xml
   <dependency>
       <groupId>io.qameta.allure</groupId>
       <artifactId>allure-java-commons</artifactId>
       <version>2.20.1</version> <!-- Use the latest version -->
   </dependency>
   ```
2. Add the Maven Surefire plugin to enable Allure report generation:
   ```xml
   <plugin>
       <groupId>org.apache.maven.plugins</groupId>
       <artifactId>maven-surefire-plugin</artifactId>
       <version>3.0.0-M7</version>
       <configuration>
           <systemPropertyVariables>
               <allure.results.directory>target/allure-results</allure.results.directory>
           </systemPropertyVariables>
       </configuration>
   </plugin>
   ```

---

### Gradle Configuration
If you're using Gradle, add the following dependencies to your `build.gradle`:
```groovy
dependencies {
    testImplementation 'io.qameta.allure:allure-java-commons:2.20.1' // Use the latest version
}
```

---

### Generating Allure Reports
1. **Run Tests:**  
   Execute your tests using Maven or Gradle:
   - Maven:
     ```bash
     mvn clean test
     ```
   - Gradle:
     ```bash
     ./gradlew test
     ```

2. **Generate Allure Results:**  
   Ensure that test results are saved in the `target/allure-results` directory.

3. **Generate the Report:**  
   Run the following command to generate the Allure report:
   ```bash
   allure serve target/allure-results
   ```
   This command will start a local server and open the report in your default web browser.

---

### Advanced Usage
- **Store Reports for Sharing:**
  Use `allure generate` to create a static report:
  ```bash
  allure generate target/allure-results -o target/allure-report
  ```
  The static HTML files will be located in the `target/allure-report` directory.

- **CI Integration:**
  Allure can be integrated with CI tools like Jenkins, GitHub Actions, and GitLab CI/CD for automated reporting.

---
