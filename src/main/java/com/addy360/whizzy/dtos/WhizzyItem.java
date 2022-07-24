package com.addy360.whizzy.dtos;


import com.addy360.whizzy.enums.WhizzyLinks;
import lombok.Data;

@Data
public class WhizzyItem {
    String image;
    String text;
    String link;
    WhizzyLinks type;
}
