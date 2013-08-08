package com.ootb.server.resty.sms;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ootb.mybatis.generator.dao.BoxEntityMapper;
import com.ootb.mybatis.generator.model.BoxEntity;
import com.ootb.mybatis.generator.model.BoxEntityExample;

@Controller
public class SmsServiceImp  {
    @Autowired
    private BoxEntityMapper mapper;

	@RequestMapping(value = "sms/{limit}/{offset}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	@ResponseBody
    @Transactional(readOnly = true)
	public List<BoxEntity> getSMS(@PathVariable int limit, @PathVariable int offset,
			HttpServletResponse response , ModelMap model) {
    	BoxEntityExample ex = new BoxEntityExample();
    	ex.setOrderByClause("ID DESC");
    	return mapper.selectByExampleWithRowbounds(ex, new RowBounds(offset,limit));
	}

}