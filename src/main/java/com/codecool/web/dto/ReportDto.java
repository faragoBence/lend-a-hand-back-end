package com.codecool.web.dto;

import com.codecool.web.model.Report;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ReportDto implements Serializable {

    private int id;

    private int reporterId;

    private String reporterUsername;

    private int reportedUserId;

    private String reportedUsername;

    private int reportedAdId;

    private String reportedAdTitle;

    private String reportText;

    private LocalDateTime timestamp;

    private boolean isHandled;

    public ReportDto() {
    }

    public ReportDto(int reporterId, String reporterUsername, int reportedUserId, String reportedUsername,
                     int reportedAdId, String reportedAdTitle, String reportText, LocalDateTime timestamp,
                     boolean isHandled) {
        this.reporterId = reporterId;
        this.reporterUsername = reporterUsername;
        this.reportedUserId = reportedUserId;
        this.reportedUsername = reportedUsername;
        this.reportedAdId = reportedAdId;
        this.reportedAdTitle = reportedAdTitle;
        this.reportText = reportText;
        this.timestamp = timestamp;
        this.isHandled = isHandled;
    }

    public ReportDto(Report report) {
        this.id = report.getId();
        this.reporterId = report.getReporter().getId();
        this.reporterUsername = report.getReporter().getUserName();

        if(report.getReportedUser()!=null) {
            this.reportedUserId = report.getReportedUser().getId();
            this.reportedUsername = report.getReportedUser().getUserName();
        }

        if(report.getReportedAd()!=null) {
            this.reportedAdId = report.getReportedAd().getId();
            this.reportedAdTitle = report.getReportedAd().getTitle();
        }

        this.reportText = report.getReportText();
        this.timestamp = report.getTimestamp();
        this.isHandled = report.isHandled();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReporterId() {
        return reporterId;
    }

    public void setReporterId(int reporterId) {
        this.reporterId = reporterId;
    }

    public String getReporterUsername() {
        return reporterUsername;
    }

    public void setReporterUsername(String reporterUsername) {
        this.reporterUsername = reporterUsername;
    }

    public int getReportedUserId() {
        return reportedUserId;
    }

    public void setReportedUserId(int reportedUserId) {
        this.reportedUserId = reportedUserId;
    }

    public String getReportedUsername() {
        return reportedUsername;
    }

    public void setReportedUsername(String reportedUsername) {
        this.reportedUsername = reportedUsername;
    }

    public int getReportedAdId() {
        return reportedAdId;
    }

    public void setReportedAdId(int reportedAdId) {
        this.reportedAdId = reportedAdId;
    }

    public String getReportedAdTitle() {
        return reportedAdTitle;
    }

    public void setReportedAdTitle(String reportedAdTitle) {
        this.reportedAdTitle = reportedAdTitle;
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isHandled() {
        return isHandled;
    }

    public void setHandled(boolean handled) {
        isHandled = handled;
    }
}
