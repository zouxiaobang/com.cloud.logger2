package com.cloud.logger2.printer;

import java.io.*;

/**
 * @author xb.zou
 * @date 2018/05/06
 * @option 将Log存储到文件中
 */
public class FilePrinter extends LoggerPrinter {
    private File mFile;

    public FilePrinter(File file){
        mFile = file;
    }

    @Override
    public void print(PRINTER_TYPE type, String message) {
        message = printWithType(type) + message;
        save(mFile, message);
    }

    private void save(File file, String inputText){
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
            writer.write(inputText);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if (writer !=null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
