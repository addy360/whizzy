package com.addy360.whizzy.helpers;

import com.addy360.whizzy.dtos.WhizzyItem;
import com.addy360.whizzy.enums.WhizzyLinks;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public abstract class WhizzyPage {

    private String baseUrl = "https://www.whizztanzania.com";

    abstract WhizzyLinks getEndpoint();

    private Document getPage(String url) {

        log.info("Request to : {}", url);
        try {

            return Jsoup.connect(url).get();
        } catch (Exception e) {
            log.info("Error while fetching page : {}", e.getMessage());
            return null;
        }
    }

    List<WhizzyItem> getPageItems() {
        String url = baseUrl + getEndpoint().getEndpoint();
        Elements elements = getPage(url).getElementsByClass("container-item");
        return elements.stream().map(this::extractItem).collect(Collectors.toList());
    }

    private WhizzyItem extractItem(Element element) {
        String image = element.selectFirst("img").attr("src");
        String title = element.selectFirst("p").text();
        String link = element.selectFirst("a").attr("href");
        WhizzyItem item = new WhizzyItem();
        item.setImage(image);
        item.setLink(baseUrl + link);
        item.setText(title);
        return item;
    }

    public void getDetails(WhizzyItem whizzyItem) {
        log.info("Getting details for {}", whizzyItem);
        Document page = getPage(whizzyItem.getLink());
        Elements elementsByClass = page.getElementsByClass(getDetailClass());
        extractDetails(elementsByClass.first());
    }

    abstract String getDetailClass();

    public void extractDetails(Element element) {
        log.info("element : {}", element);
        String title = getValue(element.selectFirst(getTitleClass()));
        String company = getValue(element.selectFirst(getCompanyClass()));
        String reference = getValue(element.selectFirst(getReferenceClass()));
        String city = getValue(element.selectFirst(getAddressClass()));
        String expDate = getValue(element.selectFirst(getDeadlineClass()));
        String summary = getValue(element.selectFirst(getSummaryClass()));
        Elements attachements = element.select("a");
        log.info("title : {}", title);
        log.info("company : {}", company);
        log.info("reference : {}", reference);
        log.info("city : {}", city);
        log.info("expDate : {}", expDate);
        log.info("summary : {}", summary);
        log.info("attachements : {}", attachements.stream().map(link -> link.attr("href")).collect(Collectors.toList()));

    }

    abstract String getSummaryClass();

    abstract String getDeadlineClass();

    abstract String getAddressClass();

    abstract String getReferenceClass();

    abstract String getCompanyClass();

    abstract String getTitleClass();

    public String getValue(Element element) {
        try {
            return element.text();
        } catch (Exception e) {
            return "Not provided";
        }
    }
}
