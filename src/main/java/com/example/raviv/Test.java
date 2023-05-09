package com.example.raviv;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        String dir = "C:\\Users\\barsa\\OneDrive\\שולחן העבודה\\project BE + FE + model\\model";
        String command = "python main.py";
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command );
        builder.directory(new File(dir));

        //builder.redirectErrorStream(true);
        Process process = builder.start();

        // Read output
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();

        // Wait for process to finish
        int exitCode = process.waitFor();
        System.out.println("Process exited with code " + exitCode);

//        Runtime rt = Runtime.getRuntime();
//        try {
//            rt.exec(new String[]{"cmd.exe","/c","start"});
//
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        PythonInterpreter pythonInterpreter = new PythonInterpreter();
//            pythonInterpreter.execfile("C:\\Users\\barsa\\OneDrive\\שולחן העבודה\\project BE + FE + model\\model\\main.py");




//        ProcessBuilder builder = new ProcessBuilder("C:\\Users\\barsa\\OneDrive\\שולחן העבודה\\project BE + FE + model\\model\\main.py", "-version");
//        builder.redirectErrorStream(true);
//        Process pr = builder.start();
//
//        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
//        String line;
//        while ((line = in.readLine()) != null){
//            System.out.print(line);
//        }
//        pr.waitFor();
//        System.out.print("ok!");
//
//        in.close();
//        System.exit(0);


    }
}
