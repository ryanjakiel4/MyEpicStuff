#!/bin/bash
# Script to compile MatthewsBdayPart2 with JavaFX support

cd "$(dirname "$0")"
JAVAFX_LIB="$(pwd)/javafx-sdk-21.0.2/lib"

echo "Compiling with JavaFX..."
# Compile only the necessary files for MatthewsBdayPart2Window
javac --module-path "$JAVAFX_LIB" --add-modules javafx.media,javafx.swing \
    Command2.java \
    Ork.java \
    LikeLike.java \
    Arrow.java \
    MatthewsBdayPart2.java \
    WordMini.java \
    ArrowMini.java \
    MemoryMini.java \
    NewGameEndlessMatthew.java \
    MatthewsBdayPart2Window.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
else
    echo "Compilation failed!"
    exit 1
fi

