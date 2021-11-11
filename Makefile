all:
	javac src/*.java
	cd src/ && java checker
clean:
	rm src/*.class