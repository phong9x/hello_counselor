package org.trams.hello.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import org.trams.hello.bean.UserBusiness;
import org.trams.hello.bean.jpa.BusinessSubEntity;
import org.trams.hello.bean.jpa.UserBusinessEntity;
import org.trams.hello.bean.jpa.UserEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class UserBusinessServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public UserBusinessServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'UserBusinessEntity' to 'UserBusiness'
	 * @param userBusinessEntity
	 */
	public UserBusiness mapUserBusinessEntityToUserBusiness(UserBusinessEntity userBusinessEntity) {
		if(userBusinessEntity == null) {
			return null;
		}

		//--- Generic mapping 
		UserBusiness userBusiness = map(userBusinessEntity, UserBusiness.class);

		//--- Link mapping ( link to BusinessSub )
		if(userBusinessEntity.getBusinessSub() != null) {
			userBusiness.setBusinessSubId(userBusinessEntity.getBusinessSub().getId());
		}
		//--- Link mapping ( link to User )
		if(userBusinessEntity.getUser() != null) {
			userBusiness.setUserId(userBusinessEntity.getUser().getId());
		}
		return userBusiness;
	}
	
	/**
	 * Mapping from 'UserBusiness' to 'UserBusinessEntity'
	 * @param userBusiness
	 * @param userBusinessEntity
	 */
	public void mapUserBusinessToUserBusinessEntity(UserBusiness userBusiness, UserBusinessEntity userBusinessEntity) {
		if(userBusiness == null) {
			return;
		}

		//--- Generic mapping 
		map(userBusiness, userBusinessEntity);

		//--- Link mapping ( link : userBusiness )
		if( hasLinkToBusinessSub(userBusiness) ) {
			BusinessSubEntity businessSub1 = new BusinessSubEntity();
			businessSub1.setId( userBusiness.getBusinessSubId() );
			userBusinessEntity.setBusinessSub( businessSub1 );
		} else {
			userBusinessEntity.setBusinessSub( null );
		}

		//--- Link mapping ( link : userBusiness )
		if( hasLinkToUser(userBusiness) ) {
			UserEntity user2 = new UserEntity();
			user2.setId( userBusiness.getUserId() );
			userBusinessEntity.setUser( user2 );
		} else {
			userBusinessEntity.setUser( null );
		}

	}
	
	/**
	 * Verify that BusinessSub id is valid.
	 * @param BusinessSub BusinessSub
	 * @return boolean
	 */
	private boolean hasLinkToBusinessSub(UserBusiness userBusiness) {
		if(userBusiness.getBusinessSubId() != null) {
			return true;
		}
		return false;
	}

	/**
	 * Verify that User id is valid.
	 * @param User User
	 * @return boolean
	 */
	private boolean hasLinkToUser(UserBusiness userBusiness) {
		if(userBusiness.getUserId() != null) {
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