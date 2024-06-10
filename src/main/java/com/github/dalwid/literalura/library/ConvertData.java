package com.github.dalwid.literalura.library;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.hibernate.sql.ast.tree.expression.Collation;

import java.util.List;

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

    public <T> List<T> getList(String json, Class<T> myClass){
        CollectionType lista = mapper.getTypeFactory().constructCollectionType(List.class, myClass);

        try {
            return mapper.readValue(json, lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String extractObjectJson(String json, String obj){
        try {
            JsonNode completJson = mapper.readTree(json);
            JsonNode jsonLivro = completJson.path("results");

            return jsonLivro.toString();
        }
        catch(JsonProcessingException e){ throw new RuntimeException(e); }
    }
}
