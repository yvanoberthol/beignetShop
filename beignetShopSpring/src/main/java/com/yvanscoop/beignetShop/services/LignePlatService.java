package com.yvanscoop.beignetShop.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yvanscoop.beignetShop.entities.LignePlat;
import com.yvanscoop.beignetShop.repositories.LignePlatRepository;
import com.yvanscoop.beignetShop.services.interfaces.ServiceInterface;

@Service
@Transactional
public class LignePlatService implements ServiceInterface<LignePlat>{
	@Autowired
	private LignePlatRepository lignePlatRepository;

	@Override
	public LignePlat create(LignePlat lignePlat) {
		// TODO Auto-generated method stub
		return lignePlatRepository.save(lignePlat);
	}

	@Override
	public List<LignePlat> getAll() {
		// TODO Auto-generated method stub
		return lignePlatRepository.findAll();
	}

	@Override
	public LignePlat getOne(Long id) {
		// TODO Auto-generated method stub
		return lignePlatRepository.getOne(id);
	}

	@Override
	public LignePlat update(LignePlat lignePlat) {
		// TODO Auto-generated method stub
		return lignePlatRepository.saveAndFlush(lignePlat);
	}

	@Override
	public void delete(LignePlat lignePlat) {
		// TODO Auto-generated method stub
		
	}

}
