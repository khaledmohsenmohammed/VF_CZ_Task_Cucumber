```bash
VF_CZ_TASK
├── driver
│   └── chromedriver.exe               # ChromeDriver executable
├── src
│   ├── main
│   │   ├── Features
│   │   │   └── add_to_basket.feature   # Cucumber feature file
│   │   ├── java
│   │   └── resources                   # Additional resources (empty)
│   └── test
│       ├── java
│       │   ├── Reports
│       │   │   └── GenerateReport      # Class for generating advanced reports
│       │   ├── StepDef
│       │   │   └── D01_add_to_basket   # Step definitions for the add_to_basket.feature
│       │   └── testRunner
│       │       └── Runners             # Test runner to execute Cucumber tests
├── target                              # Directory for generated files (compiled classes, reports, etc.)
├── .gitignore                          # Git ignore file
├── pom.xml                             # Maven configuration file with dependencies
```
git clone <repository-url>
cd VF_CZ_TASK

## Dependencies
The project relies on the following key dependencies (configured in pom.xml):

Cucumber Java for BDD support.
Selenium WebDriver for browser-based testing.
TestNG as the test execution framework.
Cucumber Reporting for generating HTML reports.

## Configurations
ChromeDriver Path
Ensure chromedriver.exe is placed in the driver folder:
```bash
driver/chromedriver.exe
```
Alternatively, you can specify the path directly in the code:
```bash
System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
```
## How to Run Tests
```bash
from the testRunner open the Runners and run this class
```
## Generating Reports
Basic HTML Report
A basic HTML report is automatically generated after running the tests.

#Report Path:
```bash
target/cucumber-html-report/index.html
```
#Advanced HTML Report
To generate an advanced report, run the GenerateReport class located in the Reports package. This will convert the JSON output of Cucumber tests into a detailed HTML report.

Report Path:
```bash
target/advanced-cucumber-reports/overview-features.html
```
