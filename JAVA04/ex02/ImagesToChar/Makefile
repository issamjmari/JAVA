TARGET_DIR=target
LIB_DIR=lib
JCOMMAND=com.beust:jcommander:1.81
JCDP=com.diogonunes:JCDP:4.0.1
RESOURCE_FILE=src/resources
PACKAGE_DIR=src/java/fr/42/printer
APP_CLASS=$(PACKAGE_DIR)/app/Program.java
LOGIC_CLASS=$(PACKAGE_DIR)/logic/ConvertImageToChar.java
OUTPUT_DIR=./target
JAR_FILE=images-to-chars-printer.jar
MANIFEST_FILE=src/manifest.txt

.PHONY: all run

all: compile run

compile:
	mkdir 	-p 	${TARGET_DIR}
	javac 	-d 	$(OUTPUT_DIR) $(APP_CLASS) $(LOGIC_CLASS)
	mvn 	dependency:copy -Dartifact=${JCOMMAND} -DoutputDirectory=./lib
	mvn 	dependency:copy -Dartifact=${JCDP} -DoutputDirectory=./lib
	jar    cfm 	${JAR_FILE} ${MANIFEST_FILE} -C ${TARGET_DIR} . -C ${RESOURCE_FILE} .
	jar 	-xf ./lib/JCDP-4.0.1.jar
	jar		-xf ./lib/jcommander-1.81.jar
	mv		com target/
	mv		META-INF target/com/diogonunes/
	cp 		-r 	${RESOURCE_FILE} ${TARGET_DIR}
	mv 			${JAR_FILE} ${TARGET_DIR}

clean:
	rm -rf ${TARGET_DIR}
	rm -rf ${LIB_DIR}
