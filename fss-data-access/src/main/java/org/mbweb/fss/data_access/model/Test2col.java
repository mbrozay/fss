package org.mbweb.fss.data_access.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="test2col")
public class Test2col {
private long id;
private String text;
private String newcol;

@Id
@GeneratedValue
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
public String getNewcol() {
	return newcol;
}
public void setNewcol(String newcol) {
	this.newcol = newcol;
}


}
