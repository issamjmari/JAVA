TARGET_DIR=target
PACKAGE_DIR=src/java/fr/42/printer
APP_CLASS=$(PACKAGE_DIR)/app/Program.java
LOGIC_CLASS=$(PACKAGE_DIR)/logic/ConvertImageToChar.java
OUTPUT_DIR=./target

.PHONY: all run

all: compile run

compile:
	mkdir -p ${TARGET_DIR}
	javac -d $(OUTPUT_DIR) $(APP_CLASS) $(LOGIC_CLASS)

rm:
	rm -rf ${TARGET_DIR}
