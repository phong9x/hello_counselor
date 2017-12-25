/*
 * Created on 14 Mar 2017 ( Time 14:52:42 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import org.trams.hello.bean.MenuAdminLevel1;
import org.trams.hello.bean.jpa.MenuAdminLevel1Entity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class MenuAdminLevel1ServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public MenuAdminLevel1ServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'MenuAdminLevel1Entity' to 'MenuAdminLevel1'
	 * @param menuAdminLevel1Entity
	 */
	public MenuAdminLevel1 mapMenuAdminLevel1EntityToMenuAdminLevel1(MenuAdminLevel1Entity menuAdminLevel1Entity) {
		if(menuAdminLevel1Entity == null) {
			return null;
		}

		//--- Generic mapping 
		MenuAdminLevel1 menuAdminLevel1 = map(menuAdminLevel1Entity, MenuAdminLevel1.class);

		return menuAdminLevel1;
	}
	
	/**
	 * Mapping from 'MenuAdminLevel1' to 'MenuAdminLevel1Entity'
	 * @param menuAdminLevel1
	 * @param menuAdminLevel1Entity
	 */
	public void mapMenuAdminLevel1ToMenuAdminLevel1Entity(MenuAdminLevel1 menuAdminLevel1, MenuAdminLevel1Entity menuAdminLevel1Entity) {
		if(menuAdminLevel1 == null) {
			return;
		}

		//--- Generic mapping 
		map(menuAdminLevel1, menuAdminLevel1Entity);

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