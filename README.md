# Text Editor Application

## Overview

The Application is a JavaFX-based text editor that allows users to create, edit, and save text files using a tabbed interface.

## Resources

- Create, open and save text files.
- Tabbed interface to work with multiple files simultaneously.
- Alert to save unsaved changes when closing a tab.
- Ability to search and replace words in bulk.
- Responsive and user-friendly interface.

## Features

- Automatic saving of already saved tabs every X seconds.
- Unsaved files considered temporary in memory for possible reopening.
- Customizable layout.

## How to begin

### Prerequisites

- Java Development Kit (JDK) installed.
- JavaFX Library.
- Apache Commons Lang ( StringUtils ).

### Installation

1. Clone the repository:

```bash
git clone https://github.com/Yuri-Silva2/TextEditor.git
```

2. Navigate to the project directory:

```bash
cd text-editor
```

3. Compile and run the application:

```bash
javac -cp "dependencies\*;dependencies\javafx\lib\*" -d out src\main\java\org\texteditor\*.java src\main\java\org\texteditor\controllers\*.java src\main\java\org\texteditor\model\*.java src\main\java\org\texteditor\viewers\menu\*.java src\main\java\org\texteditor\viewers\pane\*.java src\main\java\org\texteditor\viewers\tab\*.java src\main\java\org\texteditor\viewers\tab\find\*.java

xcopy /s /i /y src\main\resources\* out\

java --module-path "dependencies\;dependencies\javafx\lib\" --add-modules javafx.controls -cp out org.texteditor.Main
```

## Use

- Launch the app and start creating/editing your text files.
- Use the tabbed interface to manage multiple files.
- Save your work using the "File" menu options.

![alt text](https://github.com/Yuri-Silva2/TextEditor/blob/master/images/desktop.png?raw=true)

