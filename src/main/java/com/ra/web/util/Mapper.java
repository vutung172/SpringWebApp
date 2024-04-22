package com.ra.web.util;

import com.ra.web.Enum.ConstStatus;
import com.ra.web.model.dto.BillDetailDto;
import com.ra.web.model.dto.BillDto;
import com.ra.web.model.dto.request.BillDetailRequest;
import com.ra.web.model.entity.BillDetailsEntity;
import com.ra.web.model.entity.BillEntity;
import com.ra.web.repository.EmployeeRepository;
import com.ra.web.service.Impl.EmployeeServiceImpl;
import com.ra.web.service.Impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Mapper {

    public <E, D> D convertEntityToDTO(E entity, Class<D> dtoClass) {
        try {
            D dto = dtoClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(entity, dto); // Using Apache Commons BeanUtils for property copying
            return dto;
        } catch (Exception ex) {
            // Handle exception accordingly
            ex.printStackTrace();
            return null;
        }
    }

    public <D, E> E convertDTOToEntity(D dtoClass, Class<E> entity) {
        try {
            E entityBase = entity.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(dtoClass, entityBase); // Using Apache Commons BeanUtils for property copying
            return entityBase;
        } catch (Exception ex) {
            // Handle exception accordingly
            ex.printStackTrace();
            return null;
        }
    }
}
