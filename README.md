# Selenium WebDriver Course - Java, Maven, TestNG

Welcome to the Selenium WebDriver Course project! This repository contains resources and examples for learning and mastering Selenium WebDriver, with practical implementations in Java, Maven, and TestNG. The course covers the key functionalities of the entire Selenium Suite, including Selenium IDE, WebDriver, and Selenium Grid.

## Table of Contents

- [Introduction](#introduction)
- [Course Overview](#course-overview)
- [Prerequisites](#prerequisites)
- [Setup and Installation](#setup-and-installation)
- [Running Tests](#running-tests)

## Introduction

Selenium WebDriver is a powerful tool for automating web browsers. This project provides step-by-step examples and code to help you learn how to use Selenium WebDriver effectively with Java. Whether you're a beginner or an experienced tester, this course will teach you how to write automation scripts for web applications.

The course includes:
- **Selenium IDE**: Introduction to recording and playback.
- **Selenium WebDriver**: Handling various web elements, synchronization, and advanced interactions.
- **Selenium Grid**: Setting up and running tests in parallel across multiple browsers.

## Course Overview

The course consists of various sections that cover the following topics:

1. **Selenium IDE**
    - Introduction to Selenium IDE.
    - Recording and executing simple tests.
    - Exporting Selenium IDE tests to WebDriver scripts.

2. **Selenium WebDriver**
    - WebDriver setup and configuration.
    - Interacting with web elements (e.g., buttons, forms, links).
    - Advanced WebDriver techniques (e.g., waits, actions, etc.).
    - Using Java with Selenium WebDriver.

3. **Selenium Grid**
    - Setting up Selenium Grid for parallel test execution.
    - Running tests across different browsers and machines.

4. **TestNG Integration**
    - Introduction to TestNG.
    - Writing test scripts and managing test cases.
    - Reporting and test execution.

## Prerequisites

To run this project, you'll need the following:

- Java 8 or higher installed on your machine.
- Maven installed (for dependency management).
- A basic understanding of Java and testing concepts.
- IDE like IntelliJ IDEA or Eclipse for writing and running tests.

## Setup and Installation

### 1. Clone the Repository

Start by cloning the repository to your local machine:

```bash
git clone https://github.com/rakesh-vardan/Learn_TA_Selenium.git
```

### 2. Install Dependencies
Navigate to the project directory and run the following command to install the necessary dependencies using Maven:

```bash
cd Learn_TA_Selenium
mvn clean install
```

### 3. Run Tests
You can run the tests using Maven and TestNG or using the IDE. To run the tests, use the following command:

```bash
mvn test
```