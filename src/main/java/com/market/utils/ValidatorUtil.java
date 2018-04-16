package com.market.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.market.exception.ParamsException;

@Component
public class ValidatorUtil<T> {
	@Autowired
	Validator globalValidator;

	public void validate(T object) throws ParamsException {
		if(object == null) {
			throw new ParamsException("参数未传递");
		}
		Set<ConstraintViolation<T>> errors = globalValidator.validate(object);
		if(errors.size() >0 ){
			for (ConstraintViolation<T> constraintViolation : errors) {
				throw new ParamsException(constraintViolation.getMessage());
			}
		}
	}
	
	public void validate(T object,@SuppressWarnings("rawtypes") Class groups) throws ParamsException {
		if(object == null) {
			throw new ParamsException("参数未传递");
		}
		Set<ConstraintViolation<T>> errors = globalValidator.validate(object,groups);
		if(errors.size() >0 ){
			for (ConstraintViolation<T> constraintViolation : errors) {
				throw new ParamsException(constraintViolation.getMessage());
			}
		}
	}
}
