build-project:
	mvn clean verify

run-project:
	mvn "-Pcargo.run" "-Drepo.path=./storage"

run-ui-tests:
	cd tests/cms-gui && mvn clean test -Dbrowser=chrome -Dheadless=true -Dbrowser_width=1440 -Dbrowser_height=1062

generate-ui-test-report:
	cd tests/cms-gui && mvn cluecumber-report:reporting

run-api-tests:
	cd tests/booking-api && mvn clean test