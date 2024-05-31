package com.github.dalwid.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(
        @JsonAlias("title")          String titulo,
        @JsonAlias("authors")        List<AuthorData> authorDataList,
        @JsonAlias("languages")      List<String> idioma,
        @JsonAlias("download_count") String totalDownloads
        ) {}
