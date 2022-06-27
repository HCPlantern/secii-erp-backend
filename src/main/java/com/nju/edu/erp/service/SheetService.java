package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.sheet.SheetVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SheetService {
    List<SheetVO> findAllSheet();
}
