package com.yvanscoop.beignetShop.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yvanscoop.beignetShop.entities.Plat;
import com.yvanscoop.beignetShop.repositories.PlatRepository;
import com.yvanscoop.beignetShop.services.interfaces.ServiceInterface;


@Service
@Transactional
public class PlatService implements ServiceInterface<Plat>{

	@Autowired
	private PlatRepository platRepository;
	
	@Override
	public Plat create(Plat plat) {
		// TODO Auto-generated method stub
		return platRepository.save(plat);
	}

	@Override
	public List<Plat> getAll() {
		// TODO Auto-generated method stub
		return platRepository.findAll();
	}

	@Override
	public Plat getOne(Long id) {
		// TODO Auto-generated method stub
		return platRepository.getOne(id);
	}

	@Override
	public Plat update(Plat plat) {
		// TODO Auto-generated method stub
		return platRepository.saveAndFlush(plat);
	}

	@Override
	public void delete(Plat plat) {
		// TODO Auto-generated method stub
		platRepository.delete(plat);
	}

}
