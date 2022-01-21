package Team76.InternetSoftwareArchitecture.dto;

import Team76.InternetSoftwareArchitecture.model.RevisionType;

public class RevisionRequestDTO {
	
	private Long revisionEntityId;
	private Double rating;
	private String comment;
	private RevisionType revisionType;
	
	public RevisionRequestDTO() {
		
	}

	public RevisionRequestDTO(Long revisionEntityId, Double rating, String comment, RevisionType revisionType) {
		super();
		this.revisionEntityId = revisionEntityId;
		this.rating = rating;
		this.comment = comment;
		this.revisionType = revisionType;
	}

	public Long getRevisionEntityId() {
		return revisionEntityId;
	}

	public void setRevisionEntityId(Long revisionEntityId) {
		this.revisionEntityId = revisionEntityId;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public RevisionType getRevisionType() {
		return revisionType;
	}

	public void setRevisionType(RevisionType revisionType) {
		this.revisionType = revisionType;
	}

	@Override
	public String toString() {
		return "RevisionRequestDTO [revisionEntityId=" + revisionEntityId + ", rating=" + rating + ", comment="
				+ comment + ", revisionType=" + revisionType + "]";
	}
	
	

}
