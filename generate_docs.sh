#!/bin/bash

SOURCE_DIR="project/src"
OUTPUT_DIR="docs"

# Check if the source directory exists
if [ ! -d "$SOURCE_DIR" ]; then
  echo "Source directory '$SOURCE_DIR' does not exist. Exiting."
  exit 1
fi

# Create the output directory if it doesn't exist
if [ ! -d "$OUTPUT_DIR" ]; then
  mkdir -p "$OUTPUT_DIR"
  echo "Created output directory '$OUTPUT_DIR'."
fi

# Generate Javadoc
echo "Generating Javadoc..."
javadoc -d "$OUTPUT_DIR" -sourcepath "$SOURCE_DIR/main/java" $(find "$SOURCE_DIR/main/java" -name "*.java")

# Check if Javadoc generation was successful
if [ $? -eq 0 ]; then
  echo "Javadoc generated successfully in '$OUTPUT_DIR'."
else
  echo "Failed to generate Javadoc. Please check for errors."
  exit 1
fi