/*
 * Created on 15 thg 12 2016 ( Time 10:18:19 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import org.trams.hello.bean.ReportDaily;
import org.trams.hello.bean.jpa.ReportDailyEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class ReportDailyServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public ReportDailyServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'ReportDailyEntity' to 'ReportDaily'
	 * @param reportDailyEntity
	 */
	public ReportDaily mapReportDailyEntityToReportDaily(ReportDailyEntity reportDailyEntity) {
		if(reportDailyEntity == null) {
			return null;
		}

		//--- Generic mapping 
		ReportDaily reportDaily = map(reportDailyEntity, ReportDaily.class);

		return reportDaily;
	}
	
	/**
	 * Mapping from 'ReportDaily' to 'ReportDailyEntity'
	 * @param reportDaily
	 * @param reportDailyEntity
	 */
	public void mapReportDailyToReportDailyEntity(ReportDaily reportDaily, ReportDailyEntity reportDailyEntity) {
		if(reportDaily == null) {
			return;
		}

		//--- Generic mapping 
		map(reportDaily, reportDailyEntity);

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