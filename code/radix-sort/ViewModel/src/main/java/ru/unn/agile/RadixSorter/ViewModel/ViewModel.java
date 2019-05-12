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
}
