package com.github.dalwid.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseDTO(List<BookData> results) {}
