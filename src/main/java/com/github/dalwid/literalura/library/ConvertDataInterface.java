package com.github.dalwid.literalura.library;

public interface ConvertDataInterface {
    <T> T toGetData(String json, Class<T> myClass);
}
