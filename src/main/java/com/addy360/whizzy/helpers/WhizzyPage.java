package com.addy360.whizzy.helpers;

import com.addy360.whizzy.dtos.WhizzyItem;
import com.addy360.whizzy.dtos.WhizzyItemDetails;
import com.addy360.whizzy.enums.WhizzyLinks;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
public abstract class WhizzyPage {

    private String baseUrl = "https://www.whizztanzania.com";

    abstract WhizzyLinks getEndpoint();

    private Document getPage(String url) {
        log.info("Sending request to : {}", url);
        try {
            return Jsoup.connect(url).get();
        } catch (Exception e) {
            log.info("Error while fetching page : {}", e.getMessage());
            return null;
        }
    }

    public List<WhizzyItem> getPageItems() {
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

    public WhizzyItemDetails getDetails(WhizzyItem whizzyItem) {
        Document page = getPage(whizzyItem.getLink());
        Elements elementsByClass = page.getElementsByClass(getDetailClass());
        return extractDetails(elementsByClass.first());
    }

    abstract String getDetailClass();

    private WhizzyItemDetails extractDetails(Element element) {
        String title = getValue(element.getElementsByClass(getTitleClass()).first());
        String company = getValue(element.getElementsByClass(getCompanyClass()).first());
        String reference = getValue(element.getElementsByClass(getReferenceClass()).first());
        String address = getValue(element.getElementsByClass(getAddressClass()).first());
        String expDate = getValue(element.getElementsByClass(getDeadlineClass()).first());
        String summary = getValue(element.getElementsByClass(getSummaryClass()).first());
        Elements attachements = element.select("a");

        WhizzyItemDetails itemDetails = new WhizzyItemDetails();
        itemDetails.setAddress(address);
        itemDetails.setCompany(company);
        itemDetails.setDeadline(expDate);
        itemDetails.setTitle(title);
        itemDetails.setReference(reference);
        itemDetails.setSummary(summary);
        itemDetails.setAttachments(attachements
                .stream()
                .map(link -> link.attr("href"))
                .filter(s -> s.toLowerCase(Locale.ROOT).startsWith("http"))
                .collect(Collectors.toList()));
        return itemDetails;
    }

    abstract String getSummaryClass();

    abstract String getDeadlineClass();

    abstract String getAddressClass();

    abstract String getReferenceClass();

    abstract String getCompanyClass();

    abstract String getTitleClass();

    private String getValue(Element element) {
        try {
            return element.text();
        } catch (Exception e) {
            return "Not provided";
        }
    }
}
