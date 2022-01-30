package az.fuzuli.gulmammadli.codingtask.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public List<String> parsePlainTextFile(MultipartFile file) {
        List<String> lines = new ArrayList<>();

        try {
            InputStream in = new ByteArrayInputStream(file.getBytes());
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return lines;
    }

}
