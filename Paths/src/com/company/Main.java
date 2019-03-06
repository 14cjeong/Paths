package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

//Documentation:
//On FileSystem
// https://docs.oracle.com/javase/8/docs/api/java/nio/file/attribute/BasicFileAttributes.html


public class Main {

    public static void main(String[] args) {
        //The following will create file1copy.txt
        try {




            //HOW TO GET A FILE'S ATTRIBUTES (AKA METADATA)
            //Below is an example of obtaining the size of the file
            Path filePath = FileSystems.getDefault().getPath("Examples", "Dir1/file1.txt");
            long size = Files.size(filePath);
            System.out.println("Size = " + size);
            //Below gets the last modified time metadata
            System.out.println("Last modified = " + Files.getLastModifiedTime(filePath));
            //How to get all of the attributes of a file
            //We can use an interface that shoots all of those methods

            System.out.println("\n");
            BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);
            System.out.println("Size = " + attrs.size());
            System.out.println("Last modified = " + attrs.lastModifiedTime());
            System.out.println("Created = " + attrs.creationTime());
            System.out.println("Is directory = " + attrs.isDirectory());
            System.out.println("Is regular file = " + attrs.isRegularFile());
            //The important thing to note is on line 31, the
            //readAttributes method, which reads the basic set of attributes
            //because that's what we set in the parameter:
            //"BasicFileAttributes.class"
            //The readAttributes method returns an instance that implements
            //the BasicFileAttributes interface. Ctrl + click to read about it



          /*  //HOW TO CREATE MULTIPLE DIRECTORIES
            Path dirToCreate = FileSystems.getDefault().getPath("Examples", "Dir2/Dir3/Dir4/Dir5/Dir6");
           Files.createDirectories(dirToCreate);
           //Usually, you would use a backslash (PC USER); but for some reason, I can
            //Use a backslash, and it still works
            //You could have also done it this way
            //Path dirToCreate = FileSystems.getDefault().getPath("Examples/Dir2/Dir3/Dir4/Dir5/Dir6/Dir7");
            //All with one " ", without a comma. If you ran it, it'll just
            //add a Dir7*/



          /* //HOW TO CREATE A DIRECTORY
            Path dirToCreate = FileSystems.getDefault().getPath("Examples", "Dir4");
            Files.createDirectory(dirToCreate);*/


         /*   //HOW TO CREATE A FILE, though usually
            //you'd write something to it
            //If you want to write anything on it,
            //you have to open a stream or channel to it

            Path fileToCreate = FileSystems.getDefault().getPath("Examples", "file1.txt");
            Files.createFile(fileToCreate);
            //you can't use createFile to create directories
            //That's pretty obvious in the fact that it says
            //"createFile"*/


        /*       //HOW TO DELETE A FILE
            Path fileToDelete = FileSystems.getDefault().getPath("Examples", "Dir1", "file1copy.txt");
            Files.delete(fileToDelete);
            //if you try to run again, you'll obviously get an exception
            // to avoid an error, you can also run
            //Files.deleteIfExists(fileToDelete);
            //you can use the delete method to delete directories, but they
            //have to be empty. And when dealing with directories, you
            //need to walk down the file tree*/


          /*  Path fileToMove = FileSystems.getDefault().getPath("Examples", "file1.txt");
            Path destination = FileSystems.getDefault().getPath("Examples", "file2.txt");
            Files.move(fileToMove, destination);
            //The above three lines moves the file and also renames it
*/


          /*  //HOW TO MOVE A FILE
            Path fileToMove = FileSystems.getDefault().getPath("Examples", "file1copy.txt");
           Path destination = FileSystems.getDefault().getPath("Examples", "Dir1", "file1copy.txt");
           Files.move(fileToMove, destination);*/

           /* Path sourceFile = FileSystems.getDefault().getPath("Examples", "file1.txt");
            Path copyFile = FileSystems.getDefault().getPath("Examples", "file1copy.txt");
            Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);

            //HOW TO COPY DIRECTORIES
            //Here's an example
            sourceFile = FileSystems.getDefault().getPath("Examples", "Dir1");
            copyFile = FileSystems.getDefault().getPath("Examples", "Dir4");
            Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);*/

        } catch(IOException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
            //After I commented out line 20 and then ran line 21
            //The exception will actually be thrown and read
            //"Examples\filecopy.txt"
            //The reason for this is because the file has already been copied
            //It's stopping us from creating duplicates.
            //Now, if you DO want to replace, you can pass an optional third
            //parameter to the copymethod.
            //Example: Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);
        }
    }
     /*   //HOW TO GET WHOLE PATH
        Path path = FileSystems.getDefault().getPath("WorkingDirectoryFile.txt");
        //This calls the static method we we made
        printFile(path);
        // --This will produce an error because the getDefault is the wrong directory, meaning it doesn't contain the SubdirectoryFile
        //Path filePath = FileSystems.getDefault().getPath("SubdirectoryFile.txt");
        //Instead, specify an additional detail, "files," which is a subdirectory of folder( or directory) "Paths"
        //Obviously, if you have more subfolders, you will continue to specify them
      //  Path filePath = FileSystems.getDefault().getPath("Files","SubdirectoryFile.txt");
        Path filePath = Paths.get(".","Files","SubdirectoryFile.txt");
       //The above is the same as the comment above it.
        System.out.println("\n");
        printFile(filePath);

        //HOW TO GET PATH OUTSIDE THE WORKING DIRECTORY
        //Now, what about a file outside of the Paths folder? You can't use
        // .getDefault() anymore
        //Here's how to do it
        filePath = Paths.get("C:\\Users\\14cjeong\\Desktop\\JavaDevelopment\\OutThere.txt.txt"); //I actually named this file OutThere.Txt ... LOL
        System.out.println("\n");
        printFile(filePath);

        filePath = Paths.get(".");
        System.out.println("\n");
        System.out.println(filePath.toAbsolutePath());
        System.out.println("\n");
        Path path2 = FileSystems.getDefault().getPath(".", "files", "..", "files", "SubdirectoryFile.txt");
        //To explain the above. It's saying to go to the current directory, then to files, then
        //back to current directory, down to files again, down to SubdirectoryFile.txt
        System.out.println(path2.normalize().toAbsolutePath());
        //The above's normalize method draws out the straight path
        //Remember that we went back and forth in line 38
        //with Normalize, everything goes in order
        printFile(path2.normalize());


        //HOW TO CREATE A PATH TO A FILE THAT DOESN'T EXIST
        //It's possible to do so
        System.out.println("\n");
        Path path3 = FileSystems.getDefault().getPath("thisFileDoesntExist.txt");
        System.out.println(path3.toAbsolutePath());

        //HOW TO CREATE A FAKE PATH
        //This is also possible to do. No, exceptions thrown
        //Unless we did something with the bufferred reader??
        Path path4 = Paths.get("C:\\Users\\14cjeong\\fakePath\\whatever.txt");
        System.out.println(path4.toAbsolutePath());

        //We're trying to see if a path exists.
        //If you run it, it'll say that Exists = true
        filePath = FileSystems.getDefault().getPath("files");
        System.out.println("\n");
        System.out.println("Exists = " + Files.exists(filePath));

        //Checking our bogus folder to see if it exists
        //it reads false
        System.out.println("\n");
        System.out.println("Exists = " + Files.exists(path4));
        //There's also a notExists method, which asks for the opposite



    }

    //C:\Users\14cjeong\Desktop\JavaDevelopment
    //Will read the contents of the file and output them on the screen
    private static void printFile(Path path) {
        //The following is a "try with resources"
        try(BufferedReader fileReader = Files.newBufferedReader(path)) {
            String line;
            while((line = fileReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }*/
}

//Extra things I learned

//Absolute paths have the complete path from the root
//Notice that there are multiple ways to get paths
    // Paths.get or FileSystems.getDefault().getPath
    //You can also put each subsequent folder into " " (in Paths.get)
    //instead of separating everything by a backslash, \\

//two dots " .. " means moving back to the parent folder


//the files class contains operations such as copy, move, delete
//the paths interface mainly deals with paths and doesn't understand
//the fileSystem