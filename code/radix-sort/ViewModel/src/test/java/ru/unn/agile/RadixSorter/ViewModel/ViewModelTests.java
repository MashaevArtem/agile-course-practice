package ru.unn.agile.RadixSorter.viewmodel;

import ru.unn.agile.RadixSorter.viewmodel.ViewModel.Status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        FakeLogger fakeLogger = new FakeLogger();
        viewModel = new ViewModel(fakeLogger);
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.getElArray());
        assertEquals("", viewModel.getSortedArrayStringRepres());
        assertEquals("", viewModel.getInputArrayStringRepres());
        assertEquals(Status.WAITING, viewModel.getCurrState());
    }

    @Test
    public void canAddOneElementToArray() {
        viewModel.setInputVal("3");
        viewModel.addProcess();

        assertEquals("[3]", viewModel.getInputArrayStringRepres());
    }

    @Test
    public void canAddSeveralElementsToArray() {
        viewModel.setInputVal("-5");
        viewModel.addProcess();
        viewModel.setInputVal("4");
        viewModel.addProcess();
        viewModel.setInputVal("2");
        viewModel.addProcess();

        assertEquals("[-5, 4, 2]", viewModel.getInputArrayStringRepres());
    }
}
