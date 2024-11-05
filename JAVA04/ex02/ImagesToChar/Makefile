TARGET_DIR=target
LIB_DIR=lib
JCOMMAND=com.beust:jcommander:1.81
JCDP=com.diogonunes:JCDP:4.0.1
RESOURCE_FILE=src/resources
PACKAGE_DIR=src/java/fr/42/printer
APP_CLASS=$(PACKAGE_DIR)/app/Program.java
LOGIC_CLASS=$(PACKAGE_DIR)/logic/ConvertImageToChar.java
OUTPUT_DIR=./target/classes
JAR_FILE=images-to-chars-printer.jar
MANIFEST_FILE=src/manifest.txt

.PHONY: all run clean

all: compile package run

compile:
	# Create output directories
	mkdir -p ${OUTPUT_DIR}

	# Download dependencies
	mvn dependency:copy -Dartifact=${JCOMMAND} -DoutputDirectory=${LIB_DIR}
	mvn dependency:copy -Dartifact=${JCDP} -DoutputDirectory=${LIB_DIR}

	# Compile application classes with the dependencies in the classpath
	javac -d ${OUTPUT_DIR} -cp "${LIB_DIR}/jcommander-1.81.jar:${LIB_DIR}/JCDP-4.0.1.jar" ${APP_CLASS} ${LOGIC_CLASS}

package:
	# Create a temporary directory for unpacking libraries
	mkdir -p ${TARGET_DIR}/temp_lib

	# Unpack the dependencies into the temp directory
	cd ${TARGET_DIR}/temp_lib && jar xf ../../${LIB_DIR}/jcommander-1.81.jar
	cd ${TARGET_DIR}/temp_lib && jar xf ../../${LIB_DIR}/JCDP-4.0.1.jar

	# Create the final JAR including both application classes and library classes
	jar cfm ${TARGET_DIR}/${JAR_FILE} ${MANIFEST_FILE} -C ${OUTPUT_DIR} . -C ${RESOURCE_FILE} . -C ${TARGET_DIR}/temp_lib .

	# Clean up the temporary directory
	rm -rf ${TARGET_DIR}/temp_lib

run:
	# Run the jar file with specified arguments
	java -jar ${TARGET_DIR}/${JAR_FILE} --white=RED --black=GREEN

clean:
	rm -rf ${TARGET_DIR}
	rm -rf ${LIB_DIR}
