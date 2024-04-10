package Entities;

import Utils.Statics;

public class Pitch {
    public int width;
    public int height;

    // change these to properties!
    public char annularChar = ' ';
    public char borderChar = '#';
    public char halfLineChar = '|';
    public char leftSideChar = ']';
    public char rightSideChar = '[';

    public Pitch(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public String[] RenderPitch() {
        String fieldAnnular = Statics.repeatChar(this.annularChar, (this.width - 3) / 2);

        String[] fieldMap = new String[this.height];

        for (int i = 0; i < this.height; i++) {
            // top / bottom line
            if (i == 0 || i == this.height - 1) {
                fieldMap[i] = Statics.repeatChar(this.borderChar, this.width);
            }
            // middle lines
            else {
                fieldMap[i] = this.leftSideChar + fieldAnnular + this.halfLineChar + fieldAnnular + this.rightSideChar;
            }

            // add new line to all bar final line
            if (i != this.height - 1) {
                fieldMap[i] += "<br>";
            }
        }

        return fieldMap;
    }
}
