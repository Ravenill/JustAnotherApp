package com.kruczek.justanotherapp.service;

import com.kruczek.justanotherapp.decoder.CsvDecoder;
import com.kruczek.justanotherapp.decoder.Decoder;
import com.kruczek.justanotherapp.decoder.XmlDecoder;
import com.kruczek.justanotherapp.exception.UnsupportedExtensionException;
import com.kruczek.justanotherapp.model.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileService {

    final private List<String> files;

    private Decoder csvDecoder;
    private Decoder xmlDecoder;

    public FileService() {
        this.files = new ArrayList<>();
        this.csvDecoder = new CsvDecoder();
        this.xmlDecoder = new XmlDecoder();
    }

    public List<Order> decodeToOrderList(String[] filesNames) {
        loadFilesNames(filesNames);

        final ArrayList<Order> orders = new ArrayList<>();
        files.forEach(file -> processFile(file, orders));

        return orders;
    }

    private void loadFilesNames(String[] filesNames) {
        files.addAll(Arrays.asList(filesNames));
    }

    private void processFile(String file, ArrayList<Order> orders) {
        switch (getExtensionFrom(file)) {
            case ".csv":
                csvDecoder.decode(file, orders);
                break;
            case ".xml":
                xmlDecoder.decode(file, orders);
                break;
            default:
                throw new UnsupportedExtensionException();
        }
    }

    private String getExtensionFrom(String file) {
        return file.substring(file.lastIndexOf("."));
    }
}
