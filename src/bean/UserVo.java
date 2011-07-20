package bean;

import halo.dao.annotation.Column;

public class UserVo {

	@Column("testuser.userid")
	private long userid;

	@Column("testuser.nick")
	private String nick;

	@Column("member.memberuserid")
	private long memberid;

	public long getMemberid() {
		return memberid;
	}

	public void setMemberid(long memberid) {
		this.memberid = memberid;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
}