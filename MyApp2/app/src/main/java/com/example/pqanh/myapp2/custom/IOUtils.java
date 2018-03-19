package com.example.pqanh.myapp2.custom;

import java.io.InputStream;
import java.io.Reader;
/**
 * Created by pqanh on 16-03-18.
 */

public class IOUtils {

    public static void closeQuietly(InputStream in)  {
        try {
            in.close();
        }catch (Exception e) {

        }
    }

    public static void closeQuietly(Reader reader)  {
        try {
            reader.close();
        }catch (Exception e) {

        }
    }

}
