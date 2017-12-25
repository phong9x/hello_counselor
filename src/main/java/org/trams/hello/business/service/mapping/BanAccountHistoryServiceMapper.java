/*
 * Created on 21 Mar 2017 ( Time 14:43:31 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import org.trams.hello.bean.BanAccountHistory;
import org.trams.hello.bean.jpa.BanAccountHistoryEntity;
import org.trams.hello.bean.jpa.UserEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class BanAccountHistoryServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public BanAccountHistoryServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'BanAccountHistoryEntity' to 'BanAccountHistory'
	 * @param banAccountHistoryEntity
	 */
	public BanAccountHistory mapBanAccountHistoryEntityToBanAccountHistory(BanAccountHistoryEntity banAccountHistoryEntity) {
		if(banAccountHistoryEntity == null) {
			return null;
		}

		//--- Generic mapping 
		BanAccountHistory banAccountHistory = map(banAccountHistoryEntity, BanAccountHistory.class);

		//--- Link mapping ( link to User )
		if(banAccountHistoryEntity.getUser() != null) {
			banAccountHistory.setAdminBanId(banAccountHistoryEntity.getUser().getId());
		}
		return banAccountHistory;
	}
	
	/**
	 * Mapping from 'BanAccountHistory' to 'BanAccountHistoryEntity'
	 * @param banAccountHistory
	 * @param banAccountHistoryEntity
	 */
	public void mapBanAccountHistoryToBanAccountHistoryEntity(BanAccountHistory banAccountHistory, BanAccountHistoryEntity banAccountHistoryEntity) {
		if(banAccountHistory == null) {
			return;
		}

		//--- Generic mapping 
		map(banAccountHistory, banAccountHistoryEntity);

		//--- Link mapping ( link : banAccountHistory )
		if( hasLinkToUser(banAccountHistory) ) {
			UserEntity user1 = new UserEntity();
			user1.setId( banAccountHistory.getAdminBanId() );
			banAccountHistoryEntity.setUser( user1 );
		} else {
			banAccountHistoryEntity.setUser( null );
		}

	}
	
	/**
	 * Verify that User id is valid.
	 * @param User User
	 * @return boolean
	 */
	private boolean hasLinkToUser(BanAccountHistory banAccountHistory) {
		if(banAccountHistory.getAdminBanId() != null) {
			return true;
		}
		return false;
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