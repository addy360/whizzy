package com.addy360.whizzy.dtos;

import lombok.Data;

import java.util.List;

@Data
public class WhizzyItemDetails {
    String title;
    String company;
    String reference;
    String address;
    String deadline;
    String summary;
    List<String> attachments;
    String type;
}
