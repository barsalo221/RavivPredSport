package com.example.raviv;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException, JSONException {
        String dir = "C:\\Users\\barsa\\OneDrive\\שולחן העבודה\\project BE + FE + model\\model";

        String command = "python predict.py";
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

    }
}
