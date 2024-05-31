package com.github.dalwid.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorData(
        @JsonAlias("name")          String nome,
        @JsonAlias("birth_year")    String dataNascimento,
        @JsonAlias("death_year")    String dataFalecimento
) {}
