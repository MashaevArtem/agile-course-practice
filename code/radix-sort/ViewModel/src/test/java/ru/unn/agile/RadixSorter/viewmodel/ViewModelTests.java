package ru.unn.agile.RadixSorter.viewmodel;

import ru.unn.agile.RadixSorter.viewmodel.ViewModel.Status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ViewModelTests 
{
    private ViewModel viewModel;

    @Before
    public void setUp() 
	{
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() 
	{
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() 
	{
        assertEquals("", viewModel.getElemArray());
        assertEquals("", viewModel.getSortedArrayStringRepres());
        assertEquals("", viewModel.getInputArrayStringRepresentation());
        assertEquals(Status.WAITING, viewModel.getCurrentState());
    }

    @Test
    public void canAddOneElementToArray() 
	{
        viewModel.setInputValue("3");
        viewModel.addProcess();

        assertEquals("[3]", viewModel.getInputArrayStringRepresentation());
    }

    @Test
    public void canAddSeveralElementsToArray() 
	{
        viewModel.setInputValue("-5");
        viewModel.addProcess();
        viewModel.setInputValue("4");
        viewModel.addProcess();
        viewModel.setInputValue("2");
        viewModel.addProcess();

        assertEquals("[-5, 4, 2]", viewModel.getInputArrayStringRepresentation());
    }

    @Test
    public void canClearArray() 
	{
        viewModel.setInputValue("7");
        viewModel.addProcess();
        viewModel.setInputValue("5");
        viewModel.addProcess();
        viewModel.setInputValue("2");
        viewModel.addProcess();
        viewModel.setInputValue("11");
        viewModel.addProcess();
        viewModel.setInputValue("9");
        viewModel.addProcess();

        viewModel.clearProcess();

        assertEquals("[]", viewModel.getSortedArrayStringRepres());
    }

    @Test
    public void  canSortOfArrayWithOneElement() 
	{
        viewModel.setInputValue("-4");
        viewModel.addProcess();

        viewModel.sort();

        assertEquals("[-4]", viewModel.getSortedArrayStringRepres());
    }

    @Test
    public void  canSortOfNonSortedBigArray() 
	{
        viewModel.setInputValue("-4");
        viewModel.addProcess();
        viewModel.setInputValue("0");
        viewModel.addProcess();
        viewModel.setInputValue("3");
        viewModel.addProcess();

        viewModel.sort();

        assertEquals("[-4, 0, 3]", viewModel.getSortedArrayStringRepres());
    }

    @Test
    public void  isWaitingStatusWithLaunch() 
	{
        assertEquals(Status.WAITING, viewModel.getCurrentState());
    }

    @Test
    public void isWaitingStatWithBeginning() 
	{
        assertEquals(ViewModel.Status.WAITING, viewModel.getCurrentState());
    }

    @Test
    public void isWaitingStateWithAddAndDelElemEmptyField() 
	{
        viewModel.setInputValue("");

        viewModel.processingAddField(1);

        assertEquals(Status.WAITING, viewModel.getCurrentState());
    }

    @Test
    public void isReadyStateWithAddElemFieldIsWriteIn() 
	{
        viewModel.setInputValue("6");

        viewModel.processingAddField(1);

        assertEquals(Status.READY, viewModel.getCurrentState());
    }

    @Test
    public void canSetBadFormatMessage() 
	{
        viewModel.setInputValue("b");

        viewModel.processingAddField(1);

        assertEquals(Status.BAD_FORMAT, viewModel.getCurrentState());
    }

    @Test
    public void canSetSuccessMessage() 
	{
        viewModel.setInputValue("-1");
        viewModel.addProcess();
        viewModel.setInputValue("2");
        viewModel.addProcess();
        viewModel.setInputValue("5");

        viewModel.sort();

        assertEquals(Status.SUCCESSFUL, viewModel.getCurrentState());
    }

    @Test
    public void isAddButtonDisabledWithLaunch() 
	{
        assertEquals(false, viewModel.isAddButtonEnabled());
    }

    @Test
    public void isClearButtonDisabledWithLaunch() 
	{
        assertEquals(false, viewModel.isClearButtonEnabled());
    }

    @Test
    public void isSortButtonDisabledWithLaunch() 
	{
        assertEquals(false, viewModel.isSortButtonEnabled());
    }

    @Test
    public void isAddButtonEnabledAddElemFieldIsCorrect() 
	{
        viewModel.setInputValue("1");
        viewModel.processingAddField(1);

        assertEquals(true, viewModel.isAddButtonEnabled());
    }

    @Test
    public void isAddButtonDisabledWithAddElemFieldIsEmpty() 
	{
        viewModel.setInputValue("");
        viewModel.processingAddField(1);

        assertEquals(false, viewModel.isAddButtonEnabled());
    }

    @Test
    public void isAddButtonDisabledWithAddElemIsInvalid() 
	{
        viewModel.setInputValue("ijijfdf");
        viewModel.processingAddField(1);

        assertEquals(false, viewModel.isAddButtonEnabled());
    }

    @Test
    public void isClearButtonEnabledWithArrayIsNotEmpty() 
	{
        viewModel.setInputValue("3");
        viewModel.addProcess();
        viewModel.setInputValue("8");
        viewModel.addProcess();

        assertEquals(true, viewModel.isClearButtonEnabled());
    }

    @Test
    public void isSortButtonEnabledWithArrayIsNotEmpty() 
	{
        viewModel.setInputValue("7");
        viewModel.addProcess();
        viewModel.setInputValue("5");
        viewModel.addProcess();

        assertEquals(true, viewModel.isSortButtonEnabled());
    }
    @Test
    public void isClearButtonDisabledWithClearArray() 
	{
        viewModel.setInputValue("2");
        viewModel.addProcess();
        viewModel.setInputValue("7");
        viewModel.addProcess();
        viewModel.setInputValue("5");
        viewModel.addProcess();

        viewModel.clearProcess();

        assertEquals(false, viewModel.isClearButtonEnabled());
    }

    @Test
    public void isSortButtonDisabledWithClearArray() 
	{
        viewModel.setInputValue("7");
        viewModel.addProcess();
        viewModel.setInputValue("3");
        viewModel.addProcess();
        viewModel.setInputValue("2");
        viewModel.addProcess();
        viewModel.setInputValue("6");
        viewModel.addProcess();

        viewModel.clearProcess();

        assertEquals(false, viewModel.isSortButtonEnabled());
    }

    @Test
    public void isClearButtonAddOneElementEnabled() 
	{
        viewModel.setInputValue("3");
        viewModel.addProcess();

        viewModel.sort();

        assertEquals(true, viewModel.isClearButtonEnabled());
    }

    @Test
    public void canChangeStateIfAddElemFieldIsCorrect() 
	{
        viewModel.setInputValue("tests");
        viewModel.processingAddField(1);
        viewModel.setInputValue("13");
        viewModel.processingAddField(1);

        assertEquals(Status.READY, viewModel.getCurrentState());
    }

    @Test
    public void canChangeStateIfAddElemFieldIsInvalid() 
	{
        viewModel.setInputValue("13");
        viewModel.processingAddField(1);
        viewModel.setInputValue("tests");
        viewModel.processingAddField(1);

        assertEquals(Status.BAD_FORMAT, viewModel.getCurrentState());
    }

    @Test
    public void canChangeStateIfAddElemFieldIsEmpty() 
	{
        viewModel.setInputValue("13");
        viewModel.processingAddField(1);
        viewModel.setInputValue("");
        viewModel.processingAddField(1);

        assertEquals(Status.WAITING, viewModel.getCurrentState());
    }
}