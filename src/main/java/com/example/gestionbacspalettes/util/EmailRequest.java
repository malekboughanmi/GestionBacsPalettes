package com.example.gestionbacspalettes.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequest {
    private String name;
    private String recipient;
    private String message;
}
