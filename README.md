# Selenium Bootcamp :rocket:

> A Kickstarter project for anyone interested in learning about selenium.

## This project illustrates the following Selenium concepts:

1. Locator Strategies
    - Walk-through of Web Technologies
        - By Id
        - By Name
        - By Class Name
        - By Link Text
        - By Partial Link Text
        - By Tag Name
        - By XPath

2. Chrome developer tool and / or SelectorsHub
3. Selenium WebDriver
    - Setting up Selenium WebDrivers for various Browsers
    - Firefox Driver (Gecko driver)
    - Chrome Driver
    - WebDriverManager _(A Library to manage driver binaries)_
4. Headless browser Testing and driver capabilities
5. Finding Elements
    - FindElement
    - FindElements
6. Navigation & Browser Tab/Window Switching
7. Handling Keyboard Interactions
8. Handling Frames, Popups and Alerts
9. Synchronization
10. Automating advanced HTML components
    - Normal & Dynamic Tables
    - Paginations / Page Navigations
    - Context Menus
    - Action Classes
    - Dropdowns
    - Select-2-Dropdowns
    - Dynamic Lists
11. Working with `JavaScriptExecutor`
12. Handling common exceptions
    - `StaleElementReferenceException`
    - `ElementNotInteractableException`
    - `MoveTargetOutOfBoundsException`
    - `NoSuchElementException`
13. Page Scrolling
    - Using `Actions` class
    - Using `JavaScriptExecutor` class
14. Handling hidden elements

## ⛏️ Built with

<a href="https://www.java.com" target="_blank"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="50" height="80"> </a>
&nbsp;&nbsp;&nbsp;
<a href="https://www.selenium.dev" target="_blank"> <img src="https://upload.wikimedia.org/wikipedia/commons/9/9f/Selenium_logo.svg" alt="selenium" width="180" height="80"> </a>

## :small_airplane: Getting Started

### :desktop_computer: Prerequisite

Before running this project, please make sure that following libraries are installed in your machine.

- [Java JDK 16 or higher](https://www.oracle.com/in/java/technologies/downloads/)
- [Node-Js LTS](https://nodejs.org/en)
- Git ([Windows](https://gitforwindows.org/) / [Mac](https://git-scm.com/download/mac))
- Download Maven from [here](https://maven.apache.org/download.cgi) & `MAVEN_HOME` as environment variable by
  following [this](https://www.qamadness.com/knowledge-base/how-to-install-maven-and-configure-environment-variables/)
  guide

### :shield: Validate Installations

To check whether all prerequisites have been correctly installed, run the following instructions and see if you get the
expected results.

```bash
java --version
```

> This should produce a result similar to the screenshot below.
>
> ![img](/installation-images/java-version-check.png)

```bash
node --version
npm --version
```

> This should produce a result similar to the screenshot below.w.
>
> ![img_1.png](/installation-images/node-version-check.png)

```bash
git --version
```

> This should produce a result similar to the screenshot below.
>
> ![img_2.png](/installation-images/git-version-check.png)

```bash
mvn --version
```

> This should produce a result similar to the screenshot below.
>
> ![img_3.png](/installation-images/maven-version-check.png)

:memo: Please keep in mind that the version number in the output of the above commands may differ from what is shown in
the screenshot, and it should represent the version number of the libraries that you have installed.

### :hammer_and_wrench: Installation

1. Clone/download this repo in your machine
    ```bash
      git clone https://github.com/prajapati-hitesh/selenium-bootcamp.git
    ```
2. Open `cmd` or `terminal` based on your machine and run following command
    ```bash
      mvn clean test
    ``` 
   > _If everything is set up correctly, you should see a browser being launched and a login scenario running
   on [The Internet](https://the-internet.herokuapp.com/) website._

3. To start writing the code, open the project in your choice of IDE. _(
   i.e. [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) or [Eclipse](https://www.eclipse.org/))_

## :magic_wand: Brought to you by

<a href="https://cpsat.agiletestingalliance.org/" target="_blank"><img src="https://cpsat-agile.b-cdn.net/wp-content/uploads/2019/06/abt-logo-unsmushed.png" width="100" height="100"></a>
&nbsp;
<a href="https://www.agiletestingalliance.org/" target="_blank"><img src="https://agiletestingalliance.b-cdn.net/wp-content/uploads/2021/02/ATA-logo.png" width="110" height="110"></a>
