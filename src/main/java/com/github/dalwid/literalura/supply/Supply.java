package com.github.dalwid.literalura.supply;

import com.github.dalwid.literalura.library.ConvertData;
import com.github.dalwid.literalura.library.UsingAPI;

public class Supply {
    private UsingAPI usingAPI = new UsingAPI();
    private ConvertData convertData = new ConvertData();

    public UsingAPI getUsingAPI() {
        return usingAPI;
    }

    public void setUsingAPI(UsingAPI usingAPI) {
        this.usingAPI = usingAPI;
    }

    public ConvertData getConvertData() {
        return convertData;
    }

    public void setConvertData(ConvertData convertData) {
        this.convertData = convertData;
    }

    public static String getADDRESS() {
        return "https://gutendex.com/books?search=";
    }
}
