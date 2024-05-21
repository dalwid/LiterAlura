package com.github.dalwid.literalura.library;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertData implements ConvertDataInterface{

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T toGetData(String json, Class<T> myClass) {
        try{
            return mapper.readValue(json, myClass);
        }
        catch(JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
