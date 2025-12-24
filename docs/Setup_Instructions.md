# LearnTrack Setup Instructions

## Prerequisites
- **Java Development Kit (JDK)**: Version 8 or higher (Recommended: JDK 17 or 21).
- **Terminal/Command Prompt**: To compile and run the code.
- **Text Editor/IDE**: VS Code, IntelliJ IDEA, or Eclipse.

## checking Java Installation
Open your terminal and run:
```bash
java -version
javac -version
```
If you see version numbers, you are good to go. If not, please install the JDK.


## Compilation and Running
1.  Navigate to the `src` directory:
    ```bash
    cd src
    ```
2.  Compile the Java files:
    ```bash
    javac com/airtribe/learntrack/Main.java
    ```
3.  Run the application:
    ```bash
    java com.airtribe.learntrack.Main
    ```

## Troubleshooting: "javac is not recognized"
If you see an error saying `'javac' is not recognized`, it means your computer doesn't know where the Java compiler is.

**Fix 1: Add Java to PATH**
1.  Search for "Environment Variables" in your Windows Start menu.
2.  Click "Edit the system environment vairaibles".
3.  Click "Environment Variables".
4.  Under "System variables", find `Path` and click "Edit".
5.  Click "New" and add the path to your JDK `bin` folder.
    - Typical path: `C:\Program Files\Java\jdk-17\bin` (version may vary).
6.  Click OK on all windows.
7.  **Restart your terminal** and try again.

## "Hello World" Test
To verify your setup, you can create a simple `Test.java` file:
```java
public class Test {
    public static void main(String[] args) {
        System.out.println("Hello, LearnTrack!");
    }
}
```
Compile and run it:
```bash
javac Test.java
java Test
```
Output should be: `Hello, LearnTrack!`
