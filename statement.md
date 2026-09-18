# Project Statement — IPL Match Predictor

## 1. Problem Statement

Predicting the likely outcome of an IPL match can be difficult when information has to be compared manually. Team quality, past performance, and other match-related factors can be represented numerically to provide a simple comparison.

The **IPL Match Predictor** project addresses this problem by providing a desktop application in which a user selects two IPL franchises and receives a projected winner together with percentage values for the two teams. In the current implementation, the comparison is based on manually configured team-strength scores.

The project is designed as an educational prototype demonstrating how data values can be connected to a graphical user interface and transformed into a simple prediction-style output.

## 2. Scope of the Project

### In Scope

- Desktop-based prediction interface using Java Swing.
- Selection of two supported IPL franchises.
- Validation to prevent the same franchise from being selected twice.
- Fixed team-strength data stored in the Java application.
- Normalization of team strengths into percentage values.
- Display of a projected winner.
- Optional loading of team logo PNG files.
- Fallback to team abbreviations if an image is unavailable.

### Out of Scope in the Current Version

- Machine-learning model training.
- Live IPL match data or APIs.
- Historical dataset processing.
- Player-level statistics.
- Toss, venue, weather, injury, and current-form features.
- Statistical calibration of the displayed percentages.
- Automated prediction evaluation against actual match results.

## 3. Target Users

The project is intended for:

- **Students** learning Java GUI development and basic prediction logic.
- **Beginners** studying object-oriented programming and event-driven desktop applications.
- **Academic project evaluators** who need a simple demonstration of a prediction-style system.
- **IPL enthusiasts** who want to experiment with a basic team-strength comparison.

## 4. High-Level Features

1. **Team Selection** — choose two IPL franchises using dropdown controls.
2. **Team Branding** — display team logos when image assets are available.
3. **Prediction Logic** — compare fixed team-strength scores.
4. **Percentage Output** — convert the two strength values into normalized percentage shares.
5. **Winner Display** — show the franchise with the higher configured strength.
6. **Input Validation** — prevent the user from predicting a match between the same team.
7. **Fallback Graphics** — display team abbreviations when logo files cannot be loaded.
8. **Desktop GUI** — provide a compact dark-themed Java Swing interface.

## 5. Expected Outcome

The completed system provides a small, self-contained Java desktop application that demonstrates selection controls, event handling, data storage, arithmetic calculations, file-based image loading, input validation, and result presentation in a graphical interface.

## 6. Future Scope

The prototype can be extended into a more realistic forecasting system by introducing historical IPL datasets, player statistics, venue and toss information, recent team form, a machine-learning training pipeline, model evaluation, and persistent data storage.
