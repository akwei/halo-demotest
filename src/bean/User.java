package bean;

import halo.dao.annotation.Column;
import halo.dao.annotation.Id;
import halo.dao.annotation.Table;

import java.util.Date;

/**
 * 目前字段类型只支持long,int,byte,short,float,char,double,String,java.util.Date
 * 
 * @author akwei
 */
// @Table(name = "user",partitionid="userPart")
// //如果通过spring实例化分表分析器，只需要指定partitionid=beanid
// @Table(name = "user", partitionClass = TestUserDbPartitionHelper.class) //
// partitionClass=指定分表分析器类型
// @Table(name = "user", partitionClass =
// DbPartitionHelperDef.class)//默认使用不分表分库的分析器，写法同下
@Table(name = "user")
// 默认使用不分表分库的分析器
public class User {

	@Id
	private long userid;

	@Column
	// 标明是与数据库对应的列，如果与数据库对应的列写法不一样包括大小写，那么就需要这样写：@Column("db_user_nick")
	private String nick;

	@Column
	private String addr;

	@Column
	private String intro;

	@Column
	private int sex;

	@Column
	private Date createtime;

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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}
}