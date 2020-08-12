
package br.com.spentTime.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorkLog {

	@SerializedName("timeSpent")
	@Expose
	private String timeSpent;
	@SerializedName("timeSpentSeconds")
	@Expose
	private Integer timeSpentSeconds;
	@SerializedName("visibility")
	@Expose
	private Visibility visibility;
	@SerializedName("comment")
	@Expose
	private Comment comment;
	@SerializedName("started")
	@Expose
	private String started;
	private Login login;
	private String issue;

	public String getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(String timeSpent) {
		this.timeSpent = timeSpent;
	}

	public Integer getTimeSpentSeconds() {
		return timeSpentSeconds;
	}

	public void setTimeSpentSeconds(Integer timeSpentSeconds) {
		this.timeSpentSeconds = timeSpentSeconds;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public String getStarted() {
		return started;
	}

	public void setStarted(String started) {
		this.started = started;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

}
