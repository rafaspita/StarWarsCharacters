package com.rspitaliere.starwarscharacters.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rspitaliere on 21/01/18.
 */

public class MovieDTO {

    @SerializedName("title")
    private String title;
    @SerializedName("episode_id")
    private Integer episodeId;
    @SerializedName("opening_crawl")
    private String openingCrawl;
    @SerializedName("director")
    private String director;
    @SerializedName("producer")
    private String producer;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("url")
    private String url;

    private String imageUrl;


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(Integer episodeId) {
        this.episodeId = episodeId;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }

    public void setOpeningCrawl(String openingCrawl) {
        this.openingCrawl = openingCrawl;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieDTO movieDTO = (MovieDTO) o;

        if (title != null ? !title.equals(movieDTO.title) : movieDTO.title != null) return false;
        if (episodeId != null ? !episodeId.equals(movieDTO.episodeId) : movieDTO.episodeId != null)
            return false;
        if (openingCrawl != null ? !openingCrawl.equals(movieDTO.openingCrawl) : movieDTO.openingCrawl != null)
            return false;
        if (director != null ? !director.equals(movieDTO.director) : movieDTO.director != null)
            return false;
        if (producer != null ? !producer.equals(movieDTO.producer) : movieDTO.producer != null)
            return false;
        return releaseDate != null ? releaseDate.equals(movieDTO.releaseDate) : movieDTO.releaseDate == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (episodeId != null ? episodeId.hashCode() : 0);
        result = 31 * result + (openingCrawl != null ? openingCrawl.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (producer != null ? producer.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
                "title='" + title + '\'' +
                ", episodeId=" + episodeId +
                ", openingCrawl='" + openingCrawl + '\'' +
                ", director='" + director + '\'' +
                ", producer='" + producer + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
