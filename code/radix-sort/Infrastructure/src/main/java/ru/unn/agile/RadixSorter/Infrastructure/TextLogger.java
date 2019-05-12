package ru.unn.agile.RadixSorter.infrastructure;

import ru.unn.agile.RadixSorter.viewmodel.ILogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TextLogger implements ILogger {
    private final BufferedWriter writer;
    private final String fpath;
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public TextLogger(final String fpath) {
        this.fpath = fpath;
        BufferedWriter logWriter = null;
        try {
            FileWriter fileWriter = new FileWriter(fpath);
            logWriter = new BufferedWriter(fileWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer = logWriter;
    }

    private static String getCurrDateTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.ENGLISH);
        return simpleDateFormat.format(calendar.getTime());
    }

    @Override
    public void log(final String messToLog) {
        try {
            writer.write(getCurrDateTime() + "  >  " + messToLog);
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public List<String> getLog() {
        ArrayList<String> loggList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fpath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String stringLine = bufferedReader.readLine();
            while (stringLine != null) {
                loggList.add(stringLine);
                stringLine = bufferedReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return loggList;
    }
}
