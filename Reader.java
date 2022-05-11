
//This class it's it's in chaarge of reading the file and printing the calculations
// Juan Eduardo Villegas Rios A00826615 
// Creation date 5/8/2022 //.m
//.b=78
//.d=8
import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;

public class Reader extends MyFile {
 MyFile files[];

 Reader() {
 }

 private void setNumFiles(int numFiles) {
  this.files = new MyFile[numFiles];
  for (int i = 0; i < numFiles; i++) {
   this.files[i] = new MyFile();
  }
 }

 public void getFiles() {
  // *THIS BLOCK GETTING ALL THE FILES IN THE DIRECTORY
  String[] pathnames;
  File f = new File(".");
  pathnames = f.list();
  int numFilesDisk = 0;
  for (String pathname : pathnames) {
   numFilesDisk++;
  }
  // *END OF THE BLOCK

  // *THIS BLOCK GETTING THE FILES FROM THE USER
  Scanner sc = new Scanner(System.in);
  System.out.println("How many files are you going to add?");
  int numFiles = 0;
  if (sc.hasNextInt()) {
   numFiles = sc.nextInt();
   sc.nextLine();
  } else {
   System.out.println("This is not a number");
   System.exit(1);
  }
  if (numFiles > numFilesDisk) {
   System.out.println("You do not have that many files in the directory where is being executed");
   System.exit(1);
  }
  String userfileNames[] = new String[numFiles];
  for (int i = 0; i < numFiles; i++) {
   userfileNames[i] = sc.nextLine(); // Asking for name in console
   isValid(userfileNames[i]); // Checking if they are valid
   int iend = userfileNames[i].indexOf(".");
   userfileNames[i] = userfileNames[i].substring(0, iend); // Getting rid off of the extension
  }
  sc.close();
  // *END OF THE BLOCK

  userfileNames = Arrays.stream(userfileNames).distinct().toArray(String[]::new);// Removing duplicates
  setNumFiles(userfileNames.length);
  for (int i = 0; i < pathnames.length; i++) {
   for (int j = 0; j < userfileNames.length; j++) {
    if (pathnames[i].contains(userfileNames[j])) {
     read(this.files[j], pathnames[i]);
    }
   }
  }
  for (int i = 0; i < userfileNames.length; i++) {
   this.files[i].print_();
  }
 }

 public void read(MyFile file, String fileName) { // This function reads the the given input file name and do the
  // calculations
  int t = 0;
  int i = 0;
  int b = 0;
  int d = 0;
  int m = 0;
  int a = 0;
  int no = 0;
  try {
   Scanner scanner = new Scanner(SetFile(fileName));
   boolean comment = false;
   while (scanner.hasNextLine()) {
    String data = scanner.nextLine();
    data = data.trim();
    if (data.startsWith("//") || "".equals(data) || "{".equals(data) || "}".equals(data) || "};".equals(data)) {
     no++;
    }
    if (comment) {
     no++;
    }
    if (data.endsWith("*/")) {
     no--;
    }
    if (data.startsWith("/*")) {
     comment = true;
     no++;
    }
    if (data.endsWith("*/")) {
     comment = false;
     no++;
    }
    if (data.contains("//.i")) {
     i++;
    }
    if (data.endsWith("//.m")) {
     m++;
    }
    if (data.contains("//.b=")) {
     data = data.replaceAll("[\\D]", "");
     if (!data.isEmpty()) {
      int number = Integer.parseInt(data);
      b += number;
     }
    }
    if (data.contains("//.d=")) {
     data = data.replaceAll("[\\D]", "");
     if (!data.isEmpty()) {
      int number = Integer.parseInt(data);
      d += number;
     }
    }
    t++;
   }
   t = t - no;
   a = t - b + d;
   scanner.close();
   file.a += a;
   file.t += t;
   file.i += i;
   file.b += b;
   file.d += d;
   file.m += m;
  } catch (FileNotFoundException e) {
   System.out.println("Uknown error");
  }

  // !AT THE END SAVE ALL TEH FILES IN AN ARRAY OF THIS CLASS
 }
}
