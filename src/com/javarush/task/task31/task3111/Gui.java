package com.javarush.task.task31.task3111;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Gui {
    private JFrame frame = null;
    private SearchFileVisitor searchFileVisitor = null;
    private List<Path> foundFiles = null;
    private JTextArea result = null;

    public Gui() {
        guiInit();
        frame = new JFrame("FileFound");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JMenuBar mb = new JMenuBar();
        mb.add(createMenuSettings());


        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.SOUTH, createSearchPanel());
        result = new JTextArea(30, 30);
        frame.getContentPane().add(new JScrollPane(result), BorderLayout.WEST);
        frame.setVisible(true);
    }

    private JPanel createSearchPanel(){
        JPanel panel = new JPanel();
        JButton startSearch = new JButton("запуск поиска");
        startSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                searchFileVisitor.setFoundFilesToZero();
                try {
                    Files.walkFileTree(Paths.get("C:/1"), searchFileVisitor);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        JButton printResult = new JButton("Вывод результатов");
        printResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (result != null) {
                    result = new JTextArea(30, 30);
                    searchFileVisitor.getFoundFiles().forEach(path -> result.append(path.toString()));
                }
            }
        });
        panel.add(startSearch);
        panel.add(printResult);
        return panel;
    }
    private JMenu createMenuSettings(){
        JMenu settings = new JMenu("Settings");

        JMenuItem maxSize = new JMenuItem("Max Size");
        JMenuItem minSize = new JMenuItem("Min Size");
        JMenuItem partOfFile = new JMenuItem("Part of file");
        JMenuItem partOfContent = new JMenuItem("Part of content");
        JMenuItem defaultMenu = new JMenuItem("To default");

        settings.add(maxSize);
        settings.add(minSize);
        settings.add(partOfFile);
        settings.add(partOfContent);
        settings.add(defaultMenu);

        maxSize.addActionListener(new MyActionListener(Variables.MAXSIZE));
        minSize.addActionListener(new MyActionListener(Variables.MINSIZE));
        partOfContent.addActionListener(new MyActionListener(Variables.PARTOFCONTENT));
        partOfFile.addActionListener(new MyActionListener(Variables.PARTOFNAME));
        defaultMenu.addActionListener(new MyActionListener(Variables.DEFAULTS));

        return settings;
    }

    private class MyActionListener implements ActionListener {
        private Variables variable;


        public MyActionListener(Variables variable) {
            this.variable = variable;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(variable.name());
            switch (variable){
                case MAXSIZE:
                    searchFileVisitor.setMaxSize(100);
                    break;
                case MINSIZE:
                    searchFileVisitor.setMinSize(0);
                    break;
                case PARTOFNAME:
                    searchFileVisitor.setPartOfName(".txt");
                    break;
                case PARTOFCONTENT:
                    searchFileVisitor.setPartOfContent("132");
                    break;
                case DEFAULTS:
                    searchFileVisitor = new SearchFileVisitor();
                    default:
            }


        }
    }

    private void guiInit() {
        searchFileVisitor = new SearchFileVisitor();
    }


}
