/*
 * Created on 29 Apr 2017 ( Time 10:52:31 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import org.trams.hello.bean.UserCertificateTemp;
import org.trams.hello.bean.jpa.UserCertificateTempEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class UserCertificateTempServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public UserCertificateTempServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'UserCertificateTempEntity' to 'UserCertificateTemp'
	 * @param userCertificateTempEntity
	 */
	public UserCertificateTemp mapUserCertificateTempEntityToUserCertificateTemp(UserCertificateTempEntity userCertificateTempEntity) {
		if(userCertificateTempEntity == null) {
			return null;
		}

		//--- Generic mapping 
		UserCertificateTemp userCertificateTemp = map(userCertificateTempEntity, UserCertificateTemp.class);

		return userCertificateTemp;
	}
	
	/**
	 * Mapping from 'UserCertificateTemp' to 'UserCertificateTempEntity'
	 * @param userCertificateTemp
	 * @param userCertificateTempEntity
	 */
	public void mapUserCertificateTempToUserCertificateTempEntity(UserCertificateTemp userCertificateTemp, UserCertificateTempEntity userCertificateTempEntity) {
		if(userCertificateTemp == null) {
			return;
		}

		//--- Generic mapping 
		map(userCertificateTemp, userCertificateTempEntity);

	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}