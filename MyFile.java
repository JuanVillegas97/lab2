
//This class it's meant to be used in another class called Reader to give it more facilty and better administration program
// Juan Eduardo Villegas Rios A00826615
// Creation date 5/8/2022 //.m
//.b=56
//.d=2
import java.io.File;
import java.util.*;

//*Things to do - Count every single line of each file then figure out the shit for same name
public class MyFile {
  protected int t, i, b, d, m, a;
  protected String category;
  private String file_Name;
  private File file;

  MyFile() {
    this.a = 0;
    this.t = 0;
    this.i = 0;
    this.b = 0;
    this.d = 0;
    this.m = 0;
    this.category = "";
    this.file_Name = "";
  }

  protected void print_() { // This function prints the desired output
    System.out.println(
        "\nNombre del archivo: " + file_Name
            + "\n--------------------------------------------" +
            "\nTOTAL: " + t +
            "\nITEMS: " + i +
            "\nBASE: " + b +
            "\nDELETED: " + d +
            "\nMODIFIED: " + m +
            "\nADDED: " + a);
  }

  protected File SetFile(String fileName) { // Returns an object file that was read from the disk //.m
    File file = new File(fileName);
    return file;
  }

  protected void isValid(String fileName) { // Returns an object file that was read from the disk //.m
    this.file_Name = fileName;
    this.file = new File(fileName);

    if (isNumeric(this.file_Name)) {
      System.out.println("This is an integer");
      System.exit(1);
    }
    if (!(this.file.exists())) {
      System.out.println("This file does not exists");
      System.exit(1);
    }
    if (this.file.length() == 0) {
      System.out.println("This file it is empty");
      System.exit(1);
    }
  }

  protected String getFilename() {
    return this.file_Name;
  }

  protected File SingleFileNameSet(String filename) { // Returns an object file meant to be read for an scanner
    this.file = new File(filename);
    return this.file;
  }

  private boolean isNumeric(String string) {
    if (string == null || string.equals("")) {
      return false;
    }
    try {
      int intValue = Integer.parseInt(string);
      return true;
    } catch (NumberFormatException e) {
    }
    return false;
  }
}