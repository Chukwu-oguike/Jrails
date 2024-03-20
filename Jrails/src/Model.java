package jrails;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;


import java.util.List;

public class Model {

    //create file to save database

    // read the data frame
    private static Integer id = 1;
    private Integer mod_id;

    public Model(){

        this.mod_id = 0; // check if this is updated after every operation
    }

    public void save() {

        if(this.mod_id == 0){
            this.createRow();
        }else if (this.checkForID(this.mod_id) == ""){
            throw new UnsupportedOperationException("Trying to replace model not in DB");

        }else{
            String [] temp = this.checkForID(this.mod_id).split(" , ");
            this.reCreateRow(Integer.parseInt(temp[0]));
        }


        /* this is an instance of the current model */

    }

    public int id() {

        return mod_id;
    }


    public static <T> T find(Class<T> c, int id) {

        Integer idd = id;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("jrails/src/test/java/jrails/DB.txt"));
            String line = reader.readLine();
            while (line != null) {
                // read next line
                if(line.contains(c.getClass().toString())  && line.contains(idd.toString())){
                    T temp = creatModel(c, line);
                    return temp;

                } else {
                    line = reader.readLine();
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public static <T> List<T> all(Class<T> c) {
        List<T> l1 = new ArrayList<>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("jrails/src/test/java/jrails/DB.txt"));
            String line = reader.readLine();
            while (line != null) {
                // read next line
                if(line.contains(c.getClass().toString())){
                    T temp = creatModel(c, line);

                    l1.add(temp);

                } else {
                    line = reader.readLine();
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return l1;

    }

    public void destroy() {

        if(this.mod_id == 0){
            throw new UnsupportedOperationException("Model not in database");
        }

        try{
            Scanner sc = new Scanner(new File("jrails/src/test/java/jrails/DB.txt"));
            StringBuffer buffer = new StringBuffer();
            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine()+System.lineSeparator());
            }
            String fileContents = buffer.toString();
            sc.close();

            String replacement = ""; //make sure that the lines include the newline character
            String oldLine = checkForID(this.mod_id);

            if (oldLine != ""){
                fileContents = fileContents.replaceAll(oldLine, replacement);

                FileWriter writer = new FileWriter("jrails/src/test/java/jrails/DB.txt");
                writer.append(fileContents);
                writer.flush();
                this.mod_id = 0;
            } else{
                throw new UnsupportedOperationException("Model Already Deleted");
            }



        } catch(Exception e){
            System.out.println("output file not found");
        }
    }

    public static void reset() {
        try {
            PrintWriter writer = new PrintWriter("jrails/src/test/java/jrails/DB.txt");
            writer.print("");
            writer.close();
        } catch(Exception e){
            System.out.print(e);
        }

    }

    private static void writeFile(String n) {

//        File file = new File("jrails/src/test/java/jrails/DB.txt");
//
//        try {
//
//            FileWriter fileWritter = new FileWriter(file, true);
//            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
//            bufferWritter.write(n + "\n");
//            bufferWritter.close();
//            fileWritter.close();
//
//        } catch (IOException ex) {
//                System.out.print("Invalid Path");
//            }

        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter("jrails/src/test/java/jrails/DB.txt", true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            pw.println(n + "\n");
            pw.flush();

            pw.close();
            bw.close();
            fw.close();
        } catch (IOException io) {
            System.out.println(io);
        }
//        }

    }

    private String checkForID(Integer model_id){

        //return empty string if not here

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("jrails/src/test/java/jrails/DB.txt"));
            String line = reader.readLine();
            while (line != null) {
                // read next line
                if(line.contains(model_id.toString())){
                    return line;
                } else {
                    line = reader.readLine();
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";

    }

    private static <T> T creatModel(Class<T> c, String line){
        // return empty string if not here
        String[] stringarray = line.split(" , ");
        int counter = 2;
        try {
            T temp = c.getDeclaredConstructor().newInstance();
            for(Field f : c.getDeclaredFields()) {
                Class type = f.getType();

                if (type.equals(Integer.TYPE) || type.equals(Boolean.TYPE) || type.equals(String.class)) {
                    Annotation[] annotations = f.getDeclaredAnnotations();
                    if (annotations.length != 0) {
                        for (Annotation annot : annotations) {
                            if (annot.equals(jrails.Column.class)) {
                                try {
                                    String[] fld = stringarray[counter].split(": ");
                                    if (fld[0] == "int"){
                                        f.set(temp,Integer.parseInt(fld[1]));

                                    }else if(fld[0] == "String"){
                                        f.set(temp,fld[1]);

                                    }else if(fld[0] == "boolean"){
                                        f.set(temp,Boolean.parseBoolean(fld[1]));

                                    }


                                } catch (Exception e) {
                                    System.out.println("Error in Field value");
                                }

                                counter = counter + 1;
                            }
                        }
                    }

                }
            }

            return temp;
        }catch(Exception e){
            System.out.println(e);
        }


        return null;

    }

    private void reCreateRow(Integer model_id){

        String row = model_id.toString() + " , " + this.getClass().toString();
        for(Field f : this.getClass().getDeclaredFields()) {
            Class type = f.getType();

            if (type.equals(Integer.TYPE) || type.equals(Boolean.TYPE) || type.equals(String.class)) {
                Annotation[] annotations = f.getDeclaredAnnotations();
                if (annotations.length != 0) {
                    for (Annotation annot : annotations) {
                        if (annot.equals(jrails.Column.class)) {
                            try {
                                Object value = f.get(this.getClass());
                                row = row + " , " + type.toString() + ": " + value.toString();
                            } catch (Exception e) {
                                System.out.println("Error in Field value");
                            }
                        }
                    }
                }

            }
        }
        try{
            Scanner sc = new Scanner(new File("jrails/src/test/java/jrails/DB.txt"));
            StringBuffer buffer = new StringBuffer();
            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine()+System.lineSeparator());
            }
            String fileContents = buffer.toString();
            sc.close();

            String replacement = row + "\n"; //make sure that the lines include the newline character
            String oldLine = checkForID(model_id);

            fileContents = fileContents.replaceAll(oldLine, replacement);

            FileWriter writer = new FileWriter("jrails/src/test/java/jrails/DB.txt");
            writer.append(fileContents);
            writer.flush();

        } catch(Exception e){
            System.out.println("output file not found");
        }


    }

    private void createRow(){

        // get fields of subclass
        String row = id.toString() + " , " + this.getClass().toString();
        for(Field f : this.getClass().getDeclaredFields()){
            Class type = f.getType();

            if(type.equals(Integer.TYPE) || type.equals(Boolean.TYPE) || type.equals(String.class)){
                Annotation[] annotations = f.getDeclaredAnnotations();
                if (annotations.length != 0){
                    for(Annotation annot: annotations){
                        if (annot.equals(jrails.Column.class)){
                            try {
                                Object value = f.get(this.getClass());
                                row = row  + " , " + type.toString() + ": " + value.toString();
                                break;
                            } catch(Exception e){
                                System.out.println("Error in Field value");
                            }
                        }
                    }
                }

                this.writeFile(row);
                this.mod_id = id;
                id++;

            }



        }

    }

}
