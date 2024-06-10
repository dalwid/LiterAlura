package com.github.dalwid.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorData(
        @JsonAlias("name")       String autor,
        @JsonAlias("birth_year") Integer anoNascimento,
        @JsonAlias("death_year") Integer anoFalecimento
) {}
