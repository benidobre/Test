package com.example.android.test.GOTUtils;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class House {

@SerializedName("url")
@Expose
private String url;
@SerializedName("name")
@Expose
private String name;
@SerializedName("region")
@Expose
private String region;
@SerializedName("coatOfArms")
@Expose
private String coatOfArms;
@SerializedName("words")
@Expose
private String words;
@SerializedName("titles")
@Expose
private List<String> titles = null;
@SerializedName("seats")
@Expose
private List<String> seats = null;
@SerializedName("currentLord")
@Expose
private String currentLord;
@SerializedName("heir")
@Expose
private String heir;
@SerializedName("overlord")
@Expose
private String overlord;
@SerializedName("founded")
@Expose
private String founded;
@SerializedName("founder")
@Expose
private String founder;
@SerializedName("diedOut")
@Expose
private String diedOut;
@SerializedName("ancestralWeapons")
@Expose
private List<String> ancestralWeapons = null;
@SerializedName("cadetBranches")
@Expose
private List<Object> cadetBranches = null;
@SerializedName("swornMembers")
@Expose
private List<String> swornMembers = null;

public String getUrl() {
return url;
}

public void setUrl(String url) {
this.url = url;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getRegion() {
return region;
}

public void setRegion(String region) {
this.region = region;
}

public String getCoatOfArms() {
return coatOfArms;
}

public void setCoatOfArms(String coatOfArms) {
this.coatOfArms = coatOfArms;
}

public String getWords() {
return words;
}

public void setWords(String words) {
this.words = words;
}

public List<String> getTitles() {
return titles;
}

public void setTitles(List<String> titles) {
this.titles = titles;
}

public List<String> getSeats() {
return seats;
}

public void setSeats(List<String> seats) {
this.seats = seats;
}

public String getCurrentLord() {
return currentLord;
}

public void setCurrentLord(String currentLord) {
this.currentLord = currentLord;
}

public String getHeir() {
return heir;
}

public void setHeir(String heir) {
this.heir = heir;
}

public String getOverlord() {
return overlord;
}

public void setOverlord(String overlord) {
this.overlord = overlord;
}

public String getFounded() {
return founded;
}

public void setFounded(String founded) {
this.founded = founded;
}

public String getFounder() {
return founder;
}

public void setFounder(String founder) {
this.founder = founder;
}

public String getDiedOut() {
return diedOut;
}

public void setDiedOut(String diedOut) {
this.diedOut = diedOut;
}

public List<String> getAncestralWeapons() {
return ancestralWeapons;
}

public void setAncestralWeapons(List<String> ancestralWeapons) {
this.ancestralWeapons = ancestralWeapons;
}

public List<Object> getCadetBranches() {
return cadetBranches;
}

public void setCadetBranches(List<Object> cadetBranches) {
this.cadetBranches = cadetBranches;
}

public List<String> getSwornMembers() {
return swornMembers;
}

public void setSwornMembers(List<String> swornMembers) {
this.swornMembers = swornMembers;
}

}