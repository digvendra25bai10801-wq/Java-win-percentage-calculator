# IPL Match Predictor

## Overview

**IPL Match Predictor** is a Java Swing desktop application that allows a user to select two Indian Premier League (IPL) teams and view a projected winner with percentage shares for both teams.

The current implementation is a **rule-based predictor**. It does not use a trained machine-learning model or live IPL data. Each supported team is assigned a fixed strength score in the Java source code, and the application converts the two selected scores into normalized percentages.

## Features

- Select two different IPL franchises from dropdown menus.
- Display team logos when the expected PNG files are present.
- Automatically fall back to team abbreviations when a logo is unavailable.
- Calculate a projected winner from predefined team-strength values.
- Display normalized percentage values for both selected teams.
- Prevent the same team from being selected on both sides.
- Provide a dark-themed desktop GUI using Java Swing.
- Start with an initial prediction automatically when the application opens.

## Technologies / Tools Used

- **Java** — application programming language.
- **Java Swing (`javax.swing`)** — desktop graphical user interface.
- **AWT (`java.awt`)** — colors, fonts, layouts, images, and UI utilities.
- **Java Collections (`Map`, `LinkedHashMap`)** — team logo and team-strength storage.
- **Git / GitHub** — source-code version control and project hosting.

### Dependencies

The project has **no external Java libraries**. It uses only classes included with the standard Java Development Kit (JDK).

## System Requirements

- Windows, macOS, or Linux.
- **JDK 8 or later**. JDK 17 or later is recommended for a current development environment.
- A terminal such as Command Prompt, PowerShell, Terminal, or Git Bash.
- Git is required only for uploading the project to GitHub.

## Project Structure

```text
IPLPredictor/
├── IPLPredictor.java      # Main Java Swing application
├── README.md              # Setup, usage, and testing documentation
├── statement.md           # Problem statement, scope, target users, features
├── REPORT.md              # Detailed project report
├── .gitignore             # Git ignore rules
└── logos/                 # Optional team logo assets
    └── README.md          # Logo naming and placement instructions
```

## Environment Setup

### 1. Install Java

Install a JDK from a trusted distribution such as Eclipse Temurin, Oracle JDK, or another OpenJDK distribution.

After installation, open a new terminal and verify both Java and the Java compiler:

```bash
java -version
javac -version
```

Both commands should print a Java version. The `javac` command is required because the project is compiled from source.

### 2. Install Git (GitHub upload only)

Install Git if it is not already available, then verify it:

```bash
git --version
```

Git is **not required to run the application**; it is only needed for source-control operations and pushing the project to GitHub.

## Download / Clone the Project

### Option A — Clone from GitHub

After the repository has been created:

```bash
git clone https://github.com/<YOUR-USERNAME>/<YOUR-REPOSITORY>.git
cd <YOUR-REPOSITORY>
```

### Option B — Download the ZIP

1. Download the repository as a ZIP file from GitHub.
2. Extract it to a folder.
3. Open a terminal in the extracted project folder.

The directory containing `IPLPredictor.java` must be the current working directory before compiling.

## Configuration

No external configuration file is required.

The application stores its team data directly in `IPLPredictor.java` inside the `setupData()` method. For example:

```java
teamStrength.put("Mumbai Indians", 90);
teamStrength.put("Chennai Super Kings", 99);
```

The project also expects optional logo files at these relative paths:

```text
logos/mi.png
logos/csk.png
logos/rcb.png
logos/kkr.png
logos/rr.png
logos/pk.png
logos/dc.png
logos/lsg.png
```

If one or more files are missing, the application still runs and displays a team abbreviation instead of the image.

## Compile and Run

Open a terminal **inside the project folder**.

### Windows Command Prompt / PowerShell

Compile the source file:

```cmd
javac IPLPredictor.java
```

Run the application:

```cmd
java IPLPredictor
```

### macOS / Linux

```bash
javac IPLPredictor.java
java IPLPredictor
```

### Clean recompile

If you have previously compiled the project and want to force a fresh build:

**Windows:**

```cmd
del IPLPredictor.class
javac IPLPredictor.java
java IPLPredictor
```

**macOS / Linux:**

```bash
rm -f IPLPredictor.class
javac IPLPredictor.java
java IPLPredictor
```

## How the Predictor Works

The current application uses fixed team-strength values. For two selected teams, it calculates:

```text
Team 1 Percentage = Team 1 Strength / (Team 1 Strength + Team 2 Strength) × 100
Team 2 Percentage = Team 2 Strength / (Team 1 Strength + Team 2 Strength) × 100
```

The team with the higher strength value is shown as the **Predicted Winner**. If both strengths are equal, Team 1 is selected because the source code uses `t1 >= t2`.

### Example

For Mumbai Indians (90) vs Chennai Super Kings (99):

```text
Total = 90 + 99 = 189
Mumbai Indians = 90 / 189 × 100 = 47.6%
Chennai Super Kings = 99 / 189 × 100 = 52.4%
```

The displayed projected winner is **Chennai Super Kings** for this configured matchup.

> These percentages represent normalized shares of the configured strength values. They are not calibrated real-world betting or match-win probabilities.

## Testing Instructions

Testing can be performed manually because the current project has no external testing framework.

### Test 1 — Application startup

1. Run `java IPLPredictor`.
2. Confirm the Swing window opens.
3. Confirm two team selectors, the `VS` label, logo/abbreviation areas, a **Predict Winner** button, and the result card are visible.

**Expected result:** The application opens without a compilation/runtime error and shows an initial prediction.

### Test 2 — Valid team selection

1. Select two different teams.
2. Click **Predict Winner**.
3. Observe the predicted winner and both percentages.

**Expected result:** The team with the larger configured strength is displayed as the predicted winner, and the percentages correspond to the normalized strength values.

### Test 3 — Same-team validation

1. Select the same team in both dropdowns.
2. Click **Predict Winner**.

**Expected result:** A warning dialog appears asking the user to select two different teams.

### Test 4 — Missing-logo fallback

1. Temporarily remove or rename one of the expected logo files.
2. Run the program again.
3. Select the affected team.

**Expected result:** The application continues to run and displays the team's abbreviation instead of a logo.

### Test 5 — Probability calculation

Use a known pair such as Mumbai Indians (90) vs Chennai Super Kings (99) and verify that the UI shows approximately `47.6%` and `52.4%`.

**Expected result:** Displayed values match the calculation documented above.

## Limitations

- Team strengths are manually assigned and static.
- No historical match dataset is loaded.
- No player-level statistics, injuries, venue, toss, weather, or recent form are considered.
- No model training or model validation is performed.
- The application is intended as an educational/demo predictor rather than a production forecasting system.
- Team-logo image assets are optional and are not required for the core prediction logic.

## Future Enhancements

- Replace static strengths with historical IPL data.
- Add a real machine-learning model and training pipeline.
- Add player and recent-form statistics.
- Add venue, toss, and weather features.
- Store data in CSV, JSON, or a database instead of hard-coding it.
- Add accuracy metrics and model evaluation.
- Add visual charts for team comparisons and historical performance.
- Package the desktop application as a distributable executable/JAR.

## GitHub Upload Instructions

From the project root:

```bash
git init
git add .
git commit -m "Initial commit - IPL Match Predictor"
git branch -M main
git remote add origin https://github.com/<YOUR-USERNAME>/<YOUR-REPOSITORY>.git
git push -u origin main
```

Replace `<YOUR-USERNAME>` and `<YOUR-REPOSITORY>` with your GitHub account and repository name.

## License

No license has been specified for this student project. Add a `LICENSE` file if a particular open-source license is required by your course or repository policy.
