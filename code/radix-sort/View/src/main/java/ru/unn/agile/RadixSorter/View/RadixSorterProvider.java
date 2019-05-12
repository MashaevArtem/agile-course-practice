package ru.unn.agile.RadixSorter.view;

import ru.unn.agile.RadixSorter.infrastructure.TextLogger;
import ru.unn.agile.RadixSorter.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public final class RadixSorterProvider {

    private ViewModel viewModel;
    private JPanel mainPanel;
    private JLabel statusLabel;
    private JTextField inputValueField;
    private JButton addElementButton;
    private JButton sortArrayButton;
    private JButton clearArrayButton;
    private JTextArea sortedArrayArea;
    private JLabel sourceArrayLabel;
    private JList<String> listLog;
    private static final String FILEPATH = "ArraySorter.log";

    public static void main(final String[] args) {
        JFrame frame = new JFrame("RadixSorterProvider");
        TextLogger logger = new TextLogger(FILEPATH);
        frame.setContentPane(new RadixSorterProvider(new ViewModel(logger)).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private RadixSorterProvider(final ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();

        addElementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                bind();
                RadixSorterProvider.this.viewModel.addProcess();
                backBind();
            }
        });

        sortArrayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                bind();
                RadixSorterProvider.this.viewModel.sort();
                backBind();
            }
        });
	}
}
