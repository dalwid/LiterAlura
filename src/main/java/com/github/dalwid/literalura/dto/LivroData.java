package com.github.dalwid.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroData(
        @JsonAlias("title")          String titulo,
        @JsonAlias("authors")        List<AutorData> authors,
        @JsonAlias("languages")      List<String> idioma,
        @JsonAlias("download_count") Double numeroDownload
        ) {}
