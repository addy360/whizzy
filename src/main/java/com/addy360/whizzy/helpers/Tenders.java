package com.addy360.whizzy.helpers;

import com.addy360.whizzy.enums.WhizzyLinks;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Tenders extends WhizzyPage {
    @Override
    WhizzyLinks getEndpoint() {
        return WhizzyLinks.TENDERS;
    }

    @Override
    String getDetailClass() {
        return "ds-1col node node-tender view-mode-default clearfix";
    }


    @Override
    String getSummaryClass() {
        return ".field-type-text-with-summary";
    }

    @Override
    String getDeadlineClass() {
        return ".field-name-field-expiry-date";
    }

    @Override
    String getAddressClass() {
        return ".field-name-field-city";
    }

    @Override
    String getReferenceClass() {
        return ".field-name-field-tender-reference";
    }

    @Override
    String getCompanyClass() {
        return ".field-name-field-company-name";
    }

    @Override
    String getTitleClass() {
        return ".tender_title";
    }
}
