package ru.unn.agile.RadixSorter.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private List<String> log = new ArrayList<>();

    @Override
    public void log(final String messToLog) {
        log.add(messToLog);
    }
    @Override
    public List<String> getLog() {
        return log;
    }
}
