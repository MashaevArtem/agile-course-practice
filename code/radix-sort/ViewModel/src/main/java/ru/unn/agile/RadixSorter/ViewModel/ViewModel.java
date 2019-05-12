package ru.unn.agile.RadixSorter.viewmodel;

import ru.unn.agile.RadixSorter.model.RadixSorter;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private String inputValue;
    private String sortedArrayStringRepres;
    private String inputArrayStringRepres;
    private String status;

    private boolean isAdButtonEnabled;
    private boolean isCleanButtonEnabled;
    private boolean isSortButtonEnabled;

    private List<Integer> sortedArray = new ArrayList<Integer>();
    private List<Integer> inputArray = new ArrayList<Integer>();
    private ILogger logger;
    public static final String ADD_LOG = "Added new element to array. The element: ";
    public static final String CLEAR_LOG = "Input array is clean now";

    public ViewModel(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }

        this.logger = logger;

        inputValue = "";
        sortedArrayStringRepres = "";
        inputArrayStringRepres = "";
        status = Status.WAITING;

        isAdButtonEnabled = false;
        isCleanButtonEnabled = false;
        isSortButtonEnabled = false;
    }

    public String getSortedArrayStringRepres() {
        return sortedArrayStringRepres;
    }

    public String getInputArrayStringRepres() {
        return inputArrayStringRepres;
    }

    public String getElArray() {
        return inputValue;
    }
    public List<String> getLog() {
        return logger.getLog();
    }

    public void setInputVal(final String inputValue) {
        if (inputValue.equals(this.inputValue)) {
            return;
        }

        this.inputValue = inputValue;
    }

    public void addProcess() {
        if (!parseInput()) {
            return;
        }

        int value = Integer.parseInt(inputValue);
        sortedArray.add(value);
        inputArray.add(value);
        changeButtonsEnabling();

        inputArrayStringRepres = sortedArray.toString();
        logger.log(ADD_LOG + inputValue);
    }

    public void clearProcess() {
        sortedArray.clear();
        inputArray.clear();
        changeButtonsEnabling();

        sortedArrayStringRepres = sortedArray.toString();
        inputArrayStringRepres = inputArray.toString();
        logger.log(CLEAR_LOG);
    }

    public void sort() {
        int[] nativeArray = new int[sortedArray.size()];


        RadixSorter.sort(nativeArray);

        for (int i = 0; i < nativeArray.length; ++i) {
            sortedArray.set(i, nativeArray[i]);
        }

        changeButtonsEnabling();
        status = Status.SUCCESSFUL;

        sortedArrayStringRepres =  sortedArray.toString();
        logger.log(sortedArrayStringRepres);
    }

    public String getCurrState() {
        return status;
    }

    public final class Status {
        public static final String WAITING = "Waiting new element";
        public static final String READY = "Ready to add new element";
        public static final String BAD_FORMAT = "Bad format";
        public static final String SUCCESSFUL = "Sort of array is successful";

        private Status() { }
    }

    public void processingAddField(final int keyCode) {
        parseInput();
    }

    private boolean parseInput() {

        if (inputValue.isEmpty()) {
            status = Status.WAITING;
            isAdButtonEnabled = false;
            return isAdButtonEnabled;
        }

        try {
            Integer.parseInt(inputValue);
            status = Status.READY;
            isAdButtonEnabled = true;
        } catch (Exception e) {
            status = Status.BAD_FORMAT;
            isAdButtonEnabled = false;
            logger.log(status);
            return isAdButtonEnabled;
        }

        return isAdButtonEnabled;
    }

    public boolean isAdButtonEnabled() {
        return isAdButtonEnabled;
    }

    public boolean isCleanButtonEnabled() {
        return isCleanButtonEnabled;
    }

    public boolean isSortButtonEnabled() {
        return isSortButtonEnabled;
    }

    private void changeButtonsEnabling() {
        if (sortedArray.isEmpty()) {
            isSortButtonEnabled = false;
            isCleanButtonEnabled = false;
        } else {
            isSortButtonEnabled = true;
            isCleanButtonEnabled = true;
        }
    }
}
