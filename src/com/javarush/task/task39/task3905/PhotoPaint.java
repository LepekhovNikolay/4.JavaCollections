package com.javarush.task.task39.task3905;

public class PhotoPaint {
    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {
        if (image == null || x >= image.length || y >= image[0].length || desiredColor ==  null || image[y][x] == desiredColor)
            return false;
        else {
            image[y][x] = desiredColor;
            return true;
        }
    }
}
