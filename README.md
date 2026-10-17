# Susu Group Tracker

Java app to track weekly Susu contributions.

## How to run
cd src
javac SusuGroup.java App.java
java App

## How tests run
cd src
javac SusuGroup.java SusuTest.java
java SusuTest

## What it does
- Accepts partial payments (30 + 20 = 50)
- Cannot close week if member owes
- Rotating payout
- Rejects duplicate, overpay, unknown member