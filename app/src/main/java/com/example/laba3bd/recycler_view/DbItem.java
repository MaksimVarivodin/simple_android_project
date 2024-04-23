package com.example.laba3bd.recycler_view;

import android.icu.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class DbItem {
    private final int id;
    private final String recordAddedTime;
    private String serviceName;
    private String description;
    private BigDecimal price;
}
