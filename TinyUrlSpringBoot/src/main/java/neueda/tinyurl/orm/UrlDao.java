package neueda.tinyurl.orm;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "url")
public class UrlDao implements 	Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "urlid")
	private int urlid;
	@Column(name = "shorturl")
	private String shortUrl;
	@Column(name = "longurl")
	private String longUrl;
	@Column(name = "ipaddress")
	private String ipAddress;
	@Column(name = "logdate")
	private Timestamp timeStamp;
	
	
	
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	public int getUrlid() {
		return urlid;
	}
	public void setUrlid(int urlid) {
		this.urlid = urlid;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	public String getLongUrl() {
		return longUrl;
	}
	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
	
	
}