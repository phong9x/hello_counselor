/*
 * Created on 14 Apr 2017 ( Time 09:58:32 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.trams.hello.bean.Bank;
import org.trams.hello.bean.jpa.BankEntity;
import org.trams.hello.business.service.BankService;
import org.trams.hello.business.service.mapping.BankServiceMapper;
import org.trams.hello.data.repository.jpa.BankJpaRepository;
/**
 * Implementation of BankService
 */
@Component
@Transactional
public class BankServiceImpl implements BankService {

	@Resource
	private BankJpaRepository bankJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private BankServiceMapper bankServiceMapper;
	
	@Override
	public Bank findById(Integer id) {
		BankEntity bankEntity = bankJpaRepository.findOne(id);
		return bankServiceMapper.mapBankEntityToBank(bankEntity);
	}

	@Override
	public Page<BankEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return bankJpaRepository.findAll(request);
	}

	@Override
	public List<Bank> findAll() {
		Iterable<BankEntity> entities = bankJpaRepository.findAll();
		List<Bank> beans = new ArrayList<Bank>();
		for(BankEntity bankEntity : entities) {
			beans.add(bankServiceMapper.mapBankEntityToBank(bankEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = bankJpaRepository.count();
		return count;
	}

	@Override
	public Bank save(Bank bank) {
		return update(bank) ;
	}

	@Override
	public Bank create(Bank bank) {
/*
		BankEntity bankEntity = bankJpaRepository.findOne(bank.getId());
		if( bankEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		bankEntity = new BankEntity();
		bankServiceMapper.mapBankToBankEntity(bank, bankEntity);
		BankEntity bankEntitySaved = bankJpaRepository.save(bankEntity);
		return bankServiceMapper.mapBankEntityToBank(bankEntitySaved);
*/
		BankEntity bankEntity = new BankEntity();
		bankServiceMapper.mapBankToBankEntity(bank, bankEntity);
		BankEntity bankEntitySaved = bankJpaRepository.save(bankEntity);
		return bankServiceMapper.mapBankEntityToBank(bankEntitySaved);
	}

	@Override
	public Bank update(Bank bank) {
		BankEntity bankEntity = bankJpaRepository.findOne(bank.getId());
		bankServiceMapper.mapBankToBankEntity(bank, bankEntity);
		BankEntity bankEntitySaved = bankJpaRepository.save(bankEntity);
		return bankServiceMapper.mapBankEntityToBank(bankEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		bankJpaRepository.delete(id);
	}

	public BankJpaRepository getBankJpaRepository() {
		return bankJpaRepository;
	}

	public void setBankJpaRepository(BankJpaRepository bankJpaRepository) {
		this.bankJpaRepository = bankJpaRepository;
	}

	public BankServiceMapper getBankServiceMapper() {
		return bankServiceMapper;
	}

	public void setBankServiceMapper(BankServiceMapper bankServiceMapper) {
		this.bankServiceMapper = bankServiceMapper;
	}

	@Override
	public Page<BankEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return bankJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public BankEntity findOneById(Integer id) {
		return bankJpaRepository.findOne(id);
	}


}
