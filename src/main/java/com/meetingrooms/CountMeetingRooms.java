package com.meetingrooms;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountMeetingRooms {
    private final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

    public static void main(String[] args) {
        System.out.println("-------------- Start CountMeetingRooms --------------");

        try {
            List<List<LocalTime>> meetingTimings = getMeetingTimings(args[0]);
            int count = getMeetingRoomCountList(meetingTimings);

            System.out.println("Number of meeting rooms required: " + count);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in provided path " + args[0]);
        }

        System.out.println("-------------- End CountMeetingRooms --------------");
    }

    protected static List<List<LocalTime>> getMeetingTimings(String filePath) throws FileNotFoundException {
        List<List<LocalTime>> meetingTimings = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        reader.lines().sorted().forEach(line -> {
            String[] startEndTimes = line.split("-");
            meetingTimings.add(Arrays.asList(LocalTime.parse(startEndTimes[0], dtf), LocalTime.parse(startEndTimes[1], dtf)));
        });

        return meetingTimings;
    }

    protected static int getMeetingRoomCountList(List<List<LocalTime>> meetingTimings) {
        if (meetingTimings.isEmpty()) {
            return 0;
        }

        if (meetingTimings.size() == 1) {
            return 1;
        }

        int count = 1;
        List<List<LocalTime>> concurrentMeetings = new ArrayList<>();
        concurrentMeetings.add(meetingTimings.get(0));
        for (int i = 1; i < meetingTimings.size(); i++) {
            List<LocalTime> meeting = meetingTimings.get(i);

            concurrentMeetings.removeIf(cm -> !meeting.get(0).isBefore(cm.get(1)));
            concurrentMeetings.add(meeting);

            if (concurrentMeetings.size() > count) {
                count = count + 1;
            }
        }

        return count;
    }
}
