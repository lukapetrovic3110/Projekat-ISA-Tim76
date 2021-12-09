package Team76.InternetSoftwareArchitecture.dto;

public class ChangePasswordDTO {
	private String oldPassword;
	private String newPassword;
	private String email;
	private String rewritePassword;

	public ChangePasswordDTO() {

	}

	public ChangePasswordDTO(String oldPassword, String newPassword, String email, String rewritePassword) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.email = email;
		this.rewritePassword = rewritePassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRewritePassword() {
		return rewritePassword;
	}

	public void setRewritePassword(String rewritePassword) {
		this.rewritePassword = rewritePassword;
	}

}
