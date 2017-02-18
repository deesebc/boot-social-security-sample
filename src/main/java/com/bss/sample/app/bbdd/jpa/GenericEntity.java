package com.bss.sample.app.bbdd.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners( AuditingEntityListener.class )
public abstract class GenericEntity<K extends Serializable> implements Serializable {

	private static final long serialVersionUID = 4037578824025696367L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "ID", unique = true, nullable = false )
	public abstract K getId();

	public abstract void setId( K id );

	// protected Date creationDate;
	// protected Date editDate;
	// protected Integer creationUser;
	// protected Integer editUser;
	//
	// /**
	// * Descripcion del campo
	// */
	// private static final long serialVersionUID = 1L;
	//
	// @Transient
	// public abstract K getId();
	//
	// public abstract void setId( K id );
	//
	// @Temporal( TemporalType.DATE )
	// @Column( name = "CREATION_DATE" )
	// @CreatedDate
	// public Date getCreationDate() {
	// return creationDate;
	// }
	//
	// public void setCreationDate( final Date creationDate ) {
	// this.creationDate = creationDate;
	// }
	//
	// @Temporal( TemporalType.DATE )
	// @Column( name = "EDIT_DATE" )
	// @LastModifiedDate
	// public Date getEditDate() {
	// return editDate;
	// }
	//
	// public void setEditDate( final Date editDate ) {
	// this.editDate = editDate;
	// }
	//
	// @Column( name = "CREATION_USER" )
	// @CreatedBy
	// public Integer getCreationUser() {
	// return creationUser;
	// }
	//
	// public void setCreationUser( final Integer creationUser ) {
	// this.creationUser = creationUser;
	// }
	//
	// @Column( name = "EDIT_USER" )
	// @LastModifiedBy
	// public Integer getEditUser() {
	// return editUser;
	// }
	//
	// public void setEditUser( final Integer editUser ) {
	// this.editUser = editUser;
	// }

}
