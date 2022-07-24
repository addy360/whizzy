package com.addy360.whizzy.helpers;

import com.addy360.whizzy.enums.WhizzyLinks;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Jobs extends WhizzyPage {
    @Override
    WhizzyLinks getEndpoint() {
        return WhizzyLinks.JOBS;
    }

    @Override
    String getDetailClass() {
        return "ds-1col node node-job node-promoted view-mode-default clearfix";
    }

    @Override
    String getSummaryClass() {
        return ".field field-name-body field-type-text-with-summary field-label-hidden";
    }

    @Override
    String getDeadlineClass() {
        return ".field field-name-field-expiry-date field-type-datetime field-label-above";
    }

    @Override
    String getAddressClass() {
        return ".field field-name-field-city field-type-text field-label-above";
    }

    @Override
    String getReferenceClass() {
        return ".field field-name-field-job-category field-type-taxonomy-term-reference field-label-above";
    }

    @Override
    String getCompanyClass() {
        return ".field field-name-field-company-name field-type-text field-label-above";
    }

    @Override
    String getTitleClass() {
        return ".field field-name-title field-type-ds field-label-hidden";
    }
}
