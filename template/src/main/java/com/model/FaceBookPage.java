package com.model;

import javax.persistence.*;


/**
 * Created by varun on 20/10/15.
 */
@Entity
@Table(name = "pages")
public class FaceBookPage {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "page_id")
    private String pageId;

    @Column(name = "about")
    private String about;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "ad_campaign")
    private String adCampaign;

    @Column(name = "category")
    private String category;


    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAdCampaign() {
        return adCampaign;
    }

    public void setAdCampaign(String adCampaign) {
        this.adCampaign = adCampaign;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
