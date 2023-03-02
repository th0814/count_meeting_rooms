## Count Meeting Rooms v1.0
This project takes in a local file containing several lines as input (where each line contains the start and end time of a meeting in 24 hour format) and outputs the number of meeting rooms required.

Sample files can be found in resources folder.

## Author
Li Tianhui

## Build
This project can be built by running following command which outputs executable jar file in target folder

```shell
mvn clean install
```

## Run
Extract jar file from target folder and execute following command where filename is local file (including full file path) with meeting timings

```shell
java -jar count-meeting-rooms.jar $filename
```