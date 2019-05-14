package com.javarush.task.task31.task3111;

import javax.swing.*;
import java.awt.*;

public class RequestWindow extends JFrame {
    private String name;

    public RequestWindow(String name) throws HeadlessException {
        this.name = name;
    }
}
