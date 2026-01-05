#!/bin/bash
# Script to run MatthewsBdayPart2Window with JavaFX support

# Change to the script's directory (where the files are)
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$SCRIPT_DIR"

JAVAFX_LIB="$SCRIPT_DIR/javafx-sdk-21.0.2/lib"

# Verify we're in the right place
if [ ! -f "intro.txt" ]; then
    echo "ERROR: Cannot find intro.txt in $SCRIPT_DIR"
    echo "Please run this script from the Year2 directory"
    exit 1
fi

# Run with the working directory explicitly set
java --module-path "$JAVAFX_LIB" --add-modules javafx.media,javafx.swing -Duser.dir="$SCRIPT_DIR" MatthewsBdayPart2Window

