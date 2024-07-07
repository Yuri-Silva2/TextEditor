[JAVA_BADGE]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[JAVAFX_BADGE]: https://img.shields.io/badge/JavaFX-007396?style=for-the-badge&logo=java&logoColor=white
[APACHE_COMMONS_BADGE]: https://img.shields.io/badge/Apache%20Commons%20Lang-231F20?style=for-the-badge&logo=apache&logoColor=white

<h1 align="center" style="font-weight: bold;">Text Editor 📖</h1>

![java][JAVA_BADGE]
![javafx][JAVAFX_BADGE]
![apache commons][APACHE_COMMONS_BADGE]

<p align="center">
  <a href="#resources">Resources</a> •
 <a href="#future_updates">Future Updates</a> •
 <a href="#started">Started</a> •
  <a href="#cloning">Cloning</a> •
  <a href="#use">Use</a>
</p>

<p align="center">
  <b>The Application is a JavaFX-based text editor that allows users to create, edit, and save text files using a tabbed interface.</b>
</p>

<h2 id="resources">📌 Resources</h2>

- Create, open and save text files.
- Tabbed interface to work with multiple files simultaneously.
- Alert to save unsaved changes when closing a tab.
- Ability to search and replace words in bulk.
- Responsive and user-friendly interface.

<h2 id="future_updates">✏️ Future Updates</h2>

- Automatic saving of already saved tabs every X seconds.
- Unsaved files considered temporary in memory for possible reopening.
- Customizable layout.

<h2 id="started">🚀 Getting started</h2>

Let's start

<h3>Prerequisites</h3>

- [Java Development Kit (JDK)](https://www.oracle.com/br/java/technologies/downloads/)
- [JavaFX Library](https://openjfx.io/)
- [Apache Commons Lang](https://commons.apache.org/proper/commons-lang/)

<h3 id="cloning">Cloning</h3>

1. How to clone project

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

<h3 id="use">Use</h3>

- Launch the app and start creating/editing your text files.
- Use the tabbed interface to manage multiple files.
- Save your work using the "File" menu options.

  

