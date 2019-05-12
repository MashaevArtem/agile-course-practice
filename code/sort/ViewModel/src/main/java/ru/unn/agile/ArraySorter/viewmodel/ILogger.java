package ru.unn.agile.RadixSorter.viewmodel;

import java.util.List;

public interface ILogger {
    void log(String messageToLog);

    List<String> getLog();
}
